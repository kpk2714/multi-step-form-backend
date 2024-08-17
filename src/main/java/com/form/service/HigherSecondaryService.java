package com.form.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.form.entities.HigherSecondary;
import com.form.repository.HigherSecondaryRepository;

@Service
public class HigherSecondaryService {

	@Autowired
	private HigherSecondaryRepository highersecondaryRepository;
	
	public HigherSecondary save(HigherSecondary hs) {
		return highersecondaryRepository.save(hs);
	}
	
	public HigherSecondary getHigherSecondary(String id) {
		return highersecondaryRepository.findHigherSecondaryByUserId(id);
	}
	
	public void deleteHigherSecondary(String userId) {
		highersecondaryRepository.deleteHigherSecondaryByUserId(userId);
	}
}
