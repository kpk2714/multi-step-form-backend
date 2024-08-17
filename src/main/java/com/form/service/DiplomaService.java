package com.form.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.form.entities.Diploma;
import com.form.repository.DiplomaRepository;

@Service
public class DiplomaService {

	@Autowired
	private DiplomaRepository diplomaRepository;
	
	public Diploma save(Diploma diploma) {
		return diplomaRepository.save(diploma);
	}
	
	public Diploma getDiploma(String id) {
		return diplomaRepository.findDiplomaByUserId(id);
	}
	
	public void deleteDiploma(String userId) {
		diplomaRepository.deleteDiplomaByUserId(userId);
	}
}
