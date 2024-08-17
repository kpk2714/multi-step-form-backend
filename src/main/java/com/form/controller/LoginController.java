package com.form.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.form.entities.Token;
import com.form.entities.User;
import com.form.service.LoginService;
import com.form.service.TokenService;

@RestController
@CrossOrigin("http://localhost:4200")
public class LoginController {
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private LoginService loginService;
	
	@PostMapping("/login")
	public Token getUser(@RequestBody User user) throws Exception {
		
		String username = null;
		
		User userObj = null;
		Token token = null;
		if(user.getUsername()!=null && user.getPassword()!=null) {
			userObj = loginService.authenticatedUser(user.getUsername());
			if(userObj==null) {
				throw new Exception("User Doesn't Exist !!!");
			}
			
			if(!userObj.getPassword().equals(user.getPassword())) {
				throw new Exception("Wrong Credentials !!!");
			}
			
			username = user.getUsername();
			
			Token prevToken = tokenService.getTokenByUserId(username);
			
			if(prevToken!=null) {
				tokenService.deleteToken(prevToken.getTokenId());
				throw new Exception("Previous Session is still active !!!");
			}
			else {
				token = tokenService.generateToken(username);
				tokenService.saveToken(token);
			}
		}
		else {
			throw new NullPointerException("User's Credentials are empty !!!");
		}
		
		return token;
	}
	
	@DeleteMapping("/deleteToken/tokenId={tokenId}")
	public ResponseEntity<String> deleteToken(@PathVariable String tokenId) throws Exception {
		if(tokenId==null || tokenId.equals("null")) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		else {
			tokenService.deleteToken(tokenId);
			return ResponseEntity.status(HttpStatus.OK).build();
		}
	}
	
	
	
}
