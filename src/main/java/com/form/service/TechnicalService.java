package com.form.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.form.entities.Technical;
import com.form.repository.TechnicalRepository;

@Service
public class TechnicalService {

	@Autowired
	private TechnicalRepository technicalRepository;
	
	public Technical save(Technical technical) {
		return technicalRepository.save(technical);
	}
	
	public List<Technical> getAllTechnical(String id){
		return technicalRepository.findAllTechnicalByUserId(id);
	}
	
	public void deleteTechnical(String skillId) {
		technicalRepository.deleteTechnicalBySkillId(skillId);
	}
}
