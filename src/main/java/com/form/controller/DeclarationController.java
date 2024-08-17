package com.form.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.form.entities.Declaration;
import com.form.service.DeclarationService;
import com.form.service.TokenService;

@RestController
@CrossOrigin("http://localhost:4200")
public class DeclarationController {

	@Autowired
	private DeclarationService declarationService;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping("/saveDeclaration")
	public Declaration saveDeclaration(@RequestBody Declaration declaration , @RequestHeader("Authorization") String tokenId) throws Exception {
		
		tokenService.validateToken(declaration.getUserId(), tokenId);
		
		Declaration newDec = null;
		
		if(tokenId!=null && declaration.getUserId()!=null) {
			newDec = declarationService.getDeclarationByUserId(declaration.getUserId());
			if(newDec==null) {
				newDec = declarationService.saveDeclaration(declaration);
			}else {
				throw new Exception("Already Declared !!!");
			}
		}
		else {
			throw new Exception("Un-Authorized Access !!!");
		}
		
		return newDec;
	}
	
	@GetMapping("/getDeclaration/userId={id}")
	public Declaration getDeclaration(@PathVariable String id , @RequestHeader("Authorization") String tokenId) throws Exception {
		
		tokenService.validateToken(id, tokenId);
		
		Declaration dec = null;
		
		if(tokenId!=null && id!=null) {
			
			dec = declarationService.getDeclarationByUserId(id);
			
			if(dec==null) {
				throw new Exception("Not Declared Previously !!!");
			}
		}
		else {
			throw new Exception("Un-Authorized Access !!!");
		}
		
		return dec;
	}
	
}
