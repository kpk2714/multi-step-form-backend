package com.form.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.form.entities.Secondary;
import com.form.repository.SecondaryRepository;

@Service
public class SecondaryService {

	@Autowired
	private SecondaryRepository secondaryRepository;
	
	public Secondary save(Secondary secondary) {
		return secondaryRepository.save(secondary);
	}
	
	public Secondary getSecondary(String id) {
		return secondaryRepository.findSecondaryByUserId(id);
	}
	
	public void deleteSecondary(String userId) {
		secondaryRepository.deleteSecondaryByUserId(userId);
	}
}
