package com.form.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.form.entities.Width;
import com.form.repository.WidthRepository;

@Service
public class WidthService {

	@Autowired
	private WidthRepository widthRepository;
	
	public Width saveWidth(Width width) {
		return widthRepository.save(width);
	}
	
	public Width getWidth(String userId , String formname) {
		return widthRepository.findWidthByUserIdAndFormname(userId, formname);
	}
	
	public List<Width> getAllWidthById(String userId){
		return widthRepository.findAllWidthByUserId(userId);
	}
}
