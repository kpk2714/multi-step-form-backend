package com.form.controller;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;

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

import com.form.entities.Technical;
import com.form.service.TechnicalService;
import com.form.service.TokenService;

@RestController
@CrossOrigin("http://localhost:4200")
public class TechnicalController {

	@Autowired
	private TechnicalService technicalService;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping("/saveTechnical/userId={userId}")
	public Technical saveTechnical(@PathVariable String userId , @RequestBody Technical technical , @RequestHeader("Authorization") String tokenId) throws Exception {
		
		Technical newTechnical = null;
		
		tokenService.validateToken(technical.getUserId(), tokenId);
		
		if(technical.getUserId()!=null && tokenId!=null) {
			String skillId = "S"+technical.getCertificationName().charAt(0)+technical.getCertificationName().charAt(technical.getCertificationName().length()-1)+new DecimalFormat("000").format(new Random().nextInt(999));
			technical.setSkillId(skillId);
			newTechnical = technicalService.save(technical);
		}
		else {
			throw new Exception("Un-Authorized Access !!!");
		}
		
		return newTechnical;
	}
	
	@GetMapping("/getTechnical/userId={id}")
	public List<Technical> getAllTechnical(@PathVariable String id , @RequestHeader("Authorization") String tokenId) throws Exception{
		
		List<Technical> technical = null;
		
		tokenService.validateToken(id, tokenId);
		
		if(id!=null && tokenId!=null) {
			technical = technicalService.getAllTechnical(id);
		}
		else {
			throw new Exception("Un-Authorized Access !!!");
		}
		
		return technical;
	}
	
	@DeleteMapping("/deleteTechnical/userId={id}")
	public void deleteWork(@PathVariable String id , @RequestHeader("Authorization") String tokenId , @RequestParam("skillId") String skillId) throws Exception {
		
		tokenService.validateToken(id, tokenId);
		
		if(tokenId!=null && id!=null) {
			technicalService.deleteTechnical(skillId);
		}
		else {
			throw new Exception("Un-Authorized Access !!!");
		}
	}
}
