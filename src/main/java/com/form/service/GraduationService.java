package com.form.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.form.entities.Graduation;
import com.form.repository.GraduationRepository;

@Service
public class GraduationService {

	@Autowired
	private GraduationRepository graduationRepository;
	
	public Graduation save(Graduation graduation) {
		return graduationRepository.save(graduation);
	}
	
	public Graduation getGraduation(String id) {
		return graduationRepository.findGraduationByUserId(id);
	}
	
	public void deleteGraduation(String userId) {
		graduationRepository.deleteGraduationByUserId(userId);
	}
}
