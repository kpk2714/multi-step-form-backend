package com.form.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.form.entities.Personal;
import com.form.repository.PersonalRepository;

@Service
public class PersonalService {

	@Autowired
	private PersonalRepository personalRepository;
	
	public Personal savePersonal(Personal personal) {
		return personalRepository.save(personal);
	}
	
//	public Personal updatePersonal(Personal personal) {
//		return personalRepository.u
//	}
	
	public Personal getPersonalByUserId(String userId) {
		return personalRepository.findPersonalByUserId(userId);
	}
}
