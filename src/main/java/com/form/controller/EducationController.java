package com.form.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.form.entities.Diploma;
import com.form.entities.Graduation;
import com.form.entities.HigherSecondary;
import com.form.entities.Secondary;
import com.form.service.DiplomaService;
import com.form.service.GraduationService;
import com.form.service.HigherSecondaryService;
import com.form.service.SecondaryService;
import com.form.service.TokenService;

@RestController
@CrossOrigin("http://localhost:4200")
public class EducationController {

	@Autowired
	private SecondaryService secondaryService;
	
	@Autowired
	private HigherSecondaryService higherSecondaryService;
	
	@Autowired
	private DiplomaService diplomaService;
	
	@Autowired
	private GraduationService graduationService;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping("/save/secondary/userId={id}")
	public Secondary saveSecondary(@PathVariable String id , @RequestBody Secondary secondary , @RequestHeader("Authorization") String tokenId) throws Exception {
		
		Secondary sec = secondaryService.getSecondary(id);
		
		tokenService.validateToken(id, tokenId);
		
		if(id!=null && tokenId!=null) {
			if(sec!=null) {
				secondaryService.deleteSecondary(id);
			}
			secondary.setUserId(id);
			sec = secondaryService.save(secondary);
		}
		else {
			throw new Exception("Un-Authorized Access !!!");
		}
		
		return sec;
	}
	
	@PostMapping("/save/highsecondary/userId={id}")
	public HigherSecondary saveHighSecondary(@PathVariable String id , @RequestBody HigherSecondary hs , @RequestHeader("Authorization") String tokenId) throws Exception {
		
		HigherSecondary newHS = null;
		
		tokenService.validateToken(id, tokenId);
		
		if(id!=null && tokenId!=null) {
			
			newHS = higherSecondaryService.getHigherSecondary(id);
			
			if(newHS!=null) {
				higherSecondaryService.deleteHigherSecondary(id);
			}
			hs.setUserId(id);
			newHS = higherSecondaryService.save(hs);
		}
		else {
			throw new Exception("Un-Authorized Access !!!");
		}
		
		return newHS;
	}
	
	@PostMapping("/save/diploma/userId={id}")
	public Diploma saveDiploma(@PathVariable String id , @RequestBody Diploma diploma, @RequestHeader("Authorization") String tokenId) throws Exception {
		
		Diploma newDiploma = null;
		
		tokenService.validateToken(id, tokenId);
		
		if(id!=null && tokenId!=null) {
			
			newDiploma = diplomaService.getDiploma(id);
			
			if(newDiploma!=null) {
				diplomaService.deleteDiploma(id);
			}
			diploma.setUserId(id);
			newDiploma = diplomaService.save(diploma);
		}
		else {
			throw new Exception("Un-Authorized Access !!!");
		}
		
		return newDiploma;
	}
	
	@PostMapping("/save/graduation/userId={id}")
	public Graduation saveGraduation(@PathVariable String id , @RequestBody Graduation graduation, @RequestHeader("Authorization") String tokenId) throws Exception {
		
		Graduation newGraduation = null;
		
		tokenService.validateToken(id, tokenId);
		
		if(id!=null && tokenId!=null) {
			
			newGraduation = graduationService.getGraduation(id);
			
			if(newGraduation!=null) {
				graduationService.deleteGraduation(id);
			}
			graduation.setUserId(id);
			newGraduation = graduationService.save(graduation);
		}
		else {
			throw new Exception("Un-Authorized Access !!!");
		}
		
		return newGraduation;
	}
	
	@GetMapping("/get/secondary/userId={id}")
	public Secondary getSecondary(@PathVariable String id , @RequestHeader("Authorization") String tokenId) throws Exception {
		
		Secondary secondary = null;
		
		tokenService.validateToken(id, tokenId);
		
		if(id!=null && tokenId!=null) {
			secondary = secondaryService.getSecondary(id);
		}
		else {
			throw new Exception("Un-Authorized Access !!!");
		}
		
		return secondary;
	}
	
	@GetMapping("/get/highersecondary/userId={id}")
	public HigherSecondary getHigherSecondary(@PathVariable String id , @RequestHeader("Authorization") String tokenId) throws Exception {
		
		HigherSecondary hs = null;
		
		tokenService.validateToken(id, tokenId);
		
		if(id!=null && tokenId!=null) {
			hs = higherSecondaryService.getHigherSecondary(id);
		}
		else {
			throw new Exception("Un-Authorized Access !!!");
		}
		
		return hs;
	}
	
	@GetMapping("/get/diploma/userId={id}")
	public Diploma getDiploma(@PathVariable String id , @RequestHeader("Authorization") String tokenId) throws Exception {
		
		Diploma diploma = null;
		
		tokenService.validateToken(id, tokenId);
		
		if(id!=null && tokenId!=null) {
			diploma = diplomaService.getDiploma(id);
		}
		else {
			throw new Exception("Un-Authorized Access !!!");
		}
		
		return diploma;
	}
	
	@GetMapping("/get/graduation/userId={id}")
	public Graduation getGraduation(@PathVariable String id , @RequestHeader("Authorization") String tokenId) throws Exception {
		
		Graduation graduation = null;
		
		tokenService.validateToken(id, tokenId);
		
		if(id!=null && tokenId!=null) {
			graduation = graduationService.getGraduation(id);
		}
		else {
			throw new Exception("Un-Authorized Access !!!");
		}

		return graduation;
	}
	
	@DeleteMapping("/deleteSecondary/userId={id}")
	public void deleteSecondary(@PathVariable String id , @RequestHeader("Authorization") String tokenId) throws Exception {
		
		tokenService.validateToken(id, tokenId);
		
		if(id!=null && tokenId!=null) {
			secondaryService.deleteSecondary(id);
		}
		else {
			throw new Exception("Un-Authorized Access !!!");
		}
	}
	
	@DeleteMapping("/deleteHigherSecondary/userId={id}")
	public void deleteHigherSecondary(@PathVariable String id , @RequestHeader("Authorization") String tokenId) throws Exception {
		
		tokenService.validateToken(id, tokenId);
		
		if(id!=null && tokenId!=null) {
			higherSecondaryService.deleteHigherSecondary(id);
		}
		else {
			throw new Exception("Un-Authorized Access !!!");
		}
	}
	
	@DeleteMapping("/deleteDiploma/userId={id}")
	public void deleteDiploma(@PathVariable String id , @RequestHeader("Authorization") String tokenId) throws Exception {
		
		tokenService.validateToken(id, tokenId);
		
		if(id!=null && tokenId!=null) {
			diplomaService.deleteDiploma(id);
		}
		else {
			throw new Exception("Un-Authorized Access !!!");
		}
	}
	
	@DeleteMapping("/deleteGraduation/userId={id}")
	public void deleteGraduation(@PathVariable String id , @RequestHeader("Authorization") String tokenId) throws Exception {
		
		tokenService.validateToken(id, tokenId);
		
		if(id!=null && tokenId!=null) {
			graduationService.deleteGraduation(id);
		}
		else {
			throw new Exception("Un-Authorized Access !!!");
		}
	}
}
