package com.form.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.form.entities.WifeHus;
import com.form.repository.WifeHusRepository;

@Service
public class WifeHusService {

	@Autowired
	private WifeHusRepository wifeHusRepository;
	
	public WifeHus save(WifeHus wifeHus) {
		return wifeHusRepository.save(wifeHus);
	}
	
	public WifeHus getWifeHusByUserId(String id) {
		return wifeHusRepository.findWifeHusByUserId(id);
	}
	
	public void deleteWifeHus(String userId) {
		wifeHusRepository.deleteWifeHusByUserId(userId);
	}
}
