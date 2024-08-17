package com.form.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.form.entities.Child;
import com.form.repository.ChildRepository;

@Service
public class ChildService {

	@Autowired
	private ChildRepository childRepository;
	
	public Child save(Child child) {
		return childRepository.save(child);
	}
	
	public Child getChildByUserId(String id) {
		return childRepository.findChildByUserId(id);
	}
	
	public void deleteChild(String userId) {
		childRepository.deleteChildByUserId(userId);
	}
}
