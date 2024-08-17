package com.form.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.form.entities.Father;
import com.form.repository.FatherRepository;

@Service
public class FatherService {

	@Autowired
	private FatherRepository fatherRepository;
	
	public Father save(Father father) {
		return fatherRepository.save(father);
	}
	
	public Father getFatherByUserId(String id) {
		return fatherRepository.findFatherByUserId(id);
	}
	
	public void deleteFather(String userId) {
		fatherRepository.deleteFatherByUserId(userId);
	}
}
