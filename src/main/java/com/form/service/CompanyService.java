package com.form.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.form.entities.Company;
import com.form.repository.CompanyRepository;

@Service
public class CompanyService {

	@Autowired
	private CompanyRepository companyRepository;
	
	public Company saveCompany(Company company) {
		return companyRepository.save(company);
	}
	
	public List<Company> getAllCompanyByUserId(String id) {
		return companyRepository.findAllCompanyByUserId(id);
	}
	
	public void deleteCompany(String companyId) {
		companyRepository.deleteCompanyByCompanyId(companyId);
	}
}
