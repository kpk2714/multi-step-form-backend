package com.form.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.form.entities.Mother;
import com.form.repository.MotherRepository;

@Service
public class MotherService {

	@Autowired
	private MotherRepository motherRepository;
	
	public Mother save(Mother mother) {
		return motherRepository.save(mother);
	}
	
	public Mother getMotherByUserId(String id) {
		return motherRepository.findMotherByUserId(id);
	}
	
	public void deleteMother(String userId) {
		motherRepository.deleteMotherByUserId(userId);
	}
}
