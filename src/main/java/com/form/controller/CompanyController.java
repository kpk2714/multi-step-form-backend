package com.form.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
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

import com.form.entities.Company;
import com.form.service.CompanyService;
import com.form.service.TokenService;

@RestController
@CrossOrigin("http://localhost:4200")
public class CompanyController {

	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping("/saveCompany")
	public Company saveCompany(@RequestBody Company company , @RequestHeader("Authorization") String tokenId) throws Exception {
		
		Company newCompany = null;
		
		tokenService.validateToken(company.getUserId(), tokenId);
		
		if(company.getUserId()!=null && tokenId!=null) {
			String companyId = "C"+company.getCompanyname().charAt(0)+company.getCompanyname().charAt(company.getCompanyname().length()-1)+new DecimalFormat("000").format(new Random().nextInt(999));
			company.setCompanyId(companyId);
			newCompany = companyService.saveCompany(company);
		}
		else {
			throw new Exception("Un-Authorized Access !!!");
		}
		
		return newCompany;
	}
	
	
	@GetMapping("/getCompany/userId={id}")
	public List<Company> getCompany(@PathVariable String id , @RequestHeader("Authorization") String tokenId) throws Exception {
		
		List<Company> company = new ArrayList<>();
		
		tokenService.validateToken(id, tokenId);
		
		if(id!=null && tokenId!=null) {
			company = companyService.getAllCompanyByUserId(id);
		}
		else {
			throw new Exception("Un-Authorized Access !!!");
		}
		
		return company;
	}
	
	@DeleteMapping("/deleteWork/userId={id}")
	public void deleteWork(@PathVariable String id , @RequestHeader("Authorization") String tokenId , @RequestParam("companyId") String companyId) throws Exception {
		
		tokenService.validateToken(id, tokenId);
		
		if(tokenId!=null && id!=null) {
			companyService.deleteCompany(companyId);
		}
		else {
			throw new Exception("Un-Authorized Access !!!");
		}
	}
}
