package com.form.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.form.entities.Declaration;
import com.form.repository.DeclarationRepository;

@Service
public class DeclarationService {

	@Autowired
	private DeclarationRepository declarationRepository;
	
	public Declaration getDeclarationByUserId(String id) {
		return declarationRepository.findDeclarationByUserId(id);
	}
	
	public Declaration saveDeclaration(Declaration declaration) {
		return declarationRepository.save(declaration);
	}
}
