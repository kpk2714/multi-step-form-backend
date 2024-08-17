package com.form.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.form.entities.Language;
import com.form.repository.LanguageRepository;

@Service
public class LanguageService {

	@Autowired
	private LanguageRepository languageRepository;
	
	public Language saveLanguage(Language language) {
		return languageRepository.save(language);
	}
	
	public List<Language> getAllLanguage(String id){
		return languageRepository.findAllLanguageByUserId(id);
	}
	
	public void deleteLanguage(String id) {
		languageRepository.deleteLanguageByLanguageId(id);
	}
}
