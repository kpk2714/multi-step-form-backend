package com.form.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.form.entities.Language;
import com.form.service.LanguageService;
import com.form.service.TokenService;

@RestController
@CrossOrigin("http://localhost:4200")
public class LanguageController {

	@Autowired
	private LanguageService languageService;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping("/saveLanguage")
	public Language saveLanguage(@RequestBody Language language , @RequestHeader("Authorization") String tokenId) throws Exception {
		
		Language newLanguage = null;
		
		tokenService.validateToken(language.getUserId(), tokenId);
		
		if(language.getUserId()!=null && tokenId!=null) {
			newLanguage = languageService.saveLanguage(language);
		}
		else {
			throw new Exception("Un-Authorized Access !!!");
		}
		
		return newLanguage;
	}
	
	@GetMapping("/getLanguage/userId={id}")
	public List<Language> getLanguage(@PathVariable String id , @RequestHeader("Authorization") String tokenId) throws Exception{
		
		List<Language> languages = null;
		
		tokenService.validateToken(id, tokenId);
		
		if(id!=null && tokenId!=null) {
			languages = languageService.getAllLanguage(id);
		}
		else {
			throw new Exception("Un-Authorized Access !!!");
		}
		
		return languages;
	}
	
	@DeleteMapping("/deleteLanguage/languageId={id}")
	public void deleteLanguage(@PathVariable String id , @RequestHeader("Authorization") String tokenId , @RequestParam("userId") String userId) throws Exception {
		
		tokenService.validateToken(id, tokenId);
		
		if(tokenId!=null && id!=null) {
			languageService.deleteLanguage(id);
		}
		else {
			throw new Exception("Un-Authorized Access !!!");
		}
	}
}
