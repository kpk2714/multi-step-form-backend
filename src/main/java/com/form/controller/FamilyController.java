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

import com.form.entities.Child;
import com.form.entities.Father;
import com.form.entities.Mother;
import com.form.entities.WifeHus;
import com.form.service.ChildService;
import com.form.service.FatherService;
import com.form.service.MotherService;
import com.form.service.TokenService;
import com.form.service.WifeHusService;

@RestController
@CrossOrigin("http://localhost:4200")
public class FamilyController {
	
	@Autowired
	private FatherService fatherService;
	
	@Autowired
	private MotherService motherService;
	
	@Autowired
	private WifeHusService wifeHusService;
	
	@Autowired
	private ChildService childService;
	
	@Autowired
	private TokenService tokenService;

	@PostMapping("/saveFather")
	public Father saveFather(@RequestBody Father father , @RequestHeader("Authorization") String tokenId) throws Exception {
		
		Father newfather = null;
		
		tokenService.validateToken(father.getUserId(), tokenId);
		
		if(father.getUserId()!=null && tokenId!=null) {
			newfather = fatherService.save(father);
		}
		else {
			throw new Exception("Un-Authorized Access !!!");
		}
		
		return newfather;
	}
	
	@PostMapping("/saveMother")
	public Mother saveMother(@RequestBody Mother mother , @RequestHeader("Authorization") String tokenId) throws Exception {
		
		Mother newmother = null;
		
		tokenService.validateToken(mother.getUserId(), tokenId);
		
		if(tokenId!=null && mother.getUserId()!=null) {
			newmother = motherService.save(mother);
		}
		else {
			throw new Exception("Un-Authorized Access !!!");
		}
		
		return newmother;
	}
	
	@PostMapping("/saveWifeHus")
	public WifeHus saveWifeHus(@RequestBody WifeHus wifeHus , @RequestHeader("Authorization") String tokenId) throws Exception {
		
		WifeHus newWifeHus = null;
		
		tokenService.validateToken(wifeHus.getUserId(), tokenId);
		
		if(wifeHus.getUserId()!=null && tokenId!=null) {
			
			if(wifeHusService.getWifeHusByUserId(wifeHus.getUserId())!=null) {
				wifeHusService.deleteWifeHus(wifeHus.getUserId());
			}
			newWifeHus = wifeHusService.save(wifeHus);
		}
		else {
			throw new Exception("Un-Authorized Access !!!");
		}
		
		return newWifeHus;
	}
	
	@PostMapping("/saveChild")
	public Child saveChild(@RequestBody Child child , @RequestHeader("Authorization") String tokenId) throws Exception {
		
		Child newchild = null;
		
		tokenService.validateToken(child.getUserId(), tokenId);
		
		if(child.getUserId()!=null && tokenId!=null) {
			
			if(childService.getChildByUserId(child.getUserId())!=null) {
				childService.deleteChild(child.getUserId());
			}
			newchild = childService.save(child);
		}
		else {
			throw new Exception("Un-Authorized Access !!!");
		}
		
		return newchild;
	}
	
	@GetMapping("/getFather/userId={id}")
	public Father getFather(@PathVariable String id , @RequestHeader("Authorization") String tokenId) throws Exception {
		
		Father father = null;
		
		tokenService.validateToken(id, tokenId);
		
		if(tokenId!=null && id!=null) {
			father = fatherService.getFatherByUserId(id);
		}
		else {
			throw new Exception("Un-Authorized Access !!!");
		}
		
		return father;
	}
	
	@GetMapping("/getMother/userId={id}")
	public Mother getMother(@PathVariable String id , @RequestHeader("Authorization") String tokenId) throws Exception {
		
		Mother mother = null;
		
		tokenService.validateToken(id, tokenId);
		
		if(tokenId!=null && id!=null) {
			mother = motherService.getMotherByUserId(id);
		}
		else {
			throw new Exception("Un-Authorized Access !!!");
		}
		
		return mother;
	}
	
	@GetMapping("/getWifeHus/userId={id}")
	public WifeHus getWifeHus(@PathVariable String id , @RequestHeader("Authorization") String tokenId) throws Exception {
		
		WifeHus wifeHus = null;
		
		tokenService.validateToken(id, tokenId);
		
		if(tokenId!=null && id!=null) {
			wifeHus = wifeHusService.getWifeHusByUserId(id);
		}
		else {
			throw new Exception("Un-Authorized Access !!!");
		}
		
		return wifeHus;
	}
	
	
	@GetMapping("/getChild/userId={id}")
	public Child getChild(@PathVariable String id , @RequestHeader("Authorization") String tokenId) throws Exception {
		
		Child child = null;
		
		tokenService.validateToken(id, tokenId);
		
		if(tokenId!=null && id!=null) {
			child = childService.getChildByUserId(id);
		}
		else {
			throw new Exception("Un-Authorized Access !!!");
		}
		
		return child;
	}
	
	@DeleteMapping("/deleteFather/userId={id}")
	public void deleteFather(@PathVariable String id , @RequestHeader("Authorization") String tokenId) throws Exception {
		
		tokenService.validateToken(id, tokenId);
		
		if(tokenId!=null && id!=null) {
			fatherService.deleteFather(id);
		}
		else {
			throw new Exception("Un-Authorized Access !!!");
		}
		
	}
	
	@DeleteMapping("/deleteMother/userId={id}")
	public void deleteMother(@PathVariable String id , @RequestHeader("Authorization") String tokenId) throws Exception {
		
		tokenService.validateToken(id, tokenId);
		
		if(tokenId!=null && id!=null) {
			motherService.deleteMother(id);
		}
		else {
			throw new Exception("Un-Authorized Access !!!");
		}
	}
	
	@DeleteMapping("/deleteWifeHus/userId={id}")
	public void deleteWifeHus(@PathVariable String id , @RequestHeader("Authorization") String tokenId) throws Exception {
		
		tokenService.validateToken(id, tokenId);
		
		if(tokenId!=null && id!=null) {
			wifeHusService.deleteWifeHus(id);
		}
		else {
			throw new Exception("Un-Authorized Access !!!");
		}
	}
	
	@DeleteMapping("/deleteChild/userId={id}")
	public void deleteChild(@PathVariable String id , @RequestHeader("Authorization") String tokenId) throws Exception {
		
		tokenService.validateToken(id, tokenId);
		
		if(tokenId!=null && id!=null) {
			childService.deleteChild(id);
		}
		else {
			throw new Exception("Un-Authorized Access !!!");
		}
	}
}
