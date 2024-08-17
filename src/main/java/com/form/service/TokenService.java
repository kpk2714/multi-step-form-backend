package com.form.service;

import java.util.Base64;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.form.entities.Token;
import com.form.entities.User;
import com.form.repository.TokenRepository;

@Service
public class TokenService {

	@Autowired
	private TokenRepository tokenRepository;
	
	@Autowired
	private LoginService loginService;
	
	public Token saveToken(Token token) {
		return tokenRepository.save(token);
	}
	
	public Token generateToken(String userId) throws Exception {
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
		         + "0123456789"
		         + "abcdefghijklmnopqrstuvxyz" + "!#$^*-+";
		
		StringBuilder sb = new StringBuilder(20); 
		 
		for (int i = 0; i < 20; i++) 
		{ 
		   int index = (int)(AlphaNumericString.length() * Math.random());		 
		   sb.append(AlphaNumericString.charAt(index));
		} 
		 
		Token token = new Token();
		
		User user = loginService.authenticatedUser(userId);
		String username = user.getUsername();
		String password = user.getPassword();
		
		token.setUserId(username);
		token.setPassword(password);
		
		String encodeUsername = encode(username);
		String encodePassword = encode(password);
		String encodeToken = encode(sb.toString());
		
		String finalToken = encodeUsername + "." + encodePassword + "." + encodeToken;
		
		token.setTokenId(finalToken);
		
		Date date = new Date();
		token.setSaveTime(date);
		
		return token;
	}
	
	public void deleteToken(String tokenId) {
		tokenRepository.deleteTokenByTokenId(tokenId);
	}
	
	public Token getTokenByUserId(String userId) {
		return tokenRepository.findTokenByUserId(userId);
	}
	
	public String encode(String token) {
		return Base64.getEncoder().encodeToString(token.getBytes());
	}
	
	public String decode(String token) {
		byte[] decodeBytes = Base64.getDecoder().decode(token);
		return new String(decodeBytes);
	}
	
	public boolean isLoggedIn(String username , String password) {
		Token token = tokenRepository.findTokenByUserId(username);
		if(token!=null && token.getUserId().equals(username) && token.getPassword().equals(password)) {
			return true;
		}
		return false;
	}
	
	public boolean isExpired(String username) {
		Token token = tokenRepository.findTokenByUserId(username);
		Date date = new Date();
		Date saveTime = token.getSaveTime();

		long timeDifference = date.getTime() - saveTime.getTime();
		if(timeDifference/(60 * 1000)>=30) {
			return true;
		}
		return false;
	}
	
	public void validateToken(String userId , String tokenId) throws Exception {
		
		String token = getTokenByUserId(userId).getTokenId();
		
		if(token==null) {
			throw new Exception("Un-Authorized Exception Occurred !!!");
		}
		
		if(!token.equals(tokenId)) {
			throw new Exception("Token Mismatch Exception Occurred !!!");
		}
		
		String encodeUsername = token.substring(0,12);
		String encodePassword = token.substring(13,25);
				
		String decodedUsername = decode(encodeUsername);
		String decodePassword = decode(encodePassword);
		
		if(!loginService.verifyUser(decodedUsername, decodePassword)) {
			throw new Exception("Un-Authenticated Access Exception Occurred !!!");
		}
		
		if(!isLoggedIn(decodedUsername, decodePassword)) {
			throw new Exception("Un-Authorized Access Exception Occurred !!!");
		}
		
		if(isExpired(decodedUsername)) {
			throw new Exception("Token Expired !!!");
		}
	}
}
