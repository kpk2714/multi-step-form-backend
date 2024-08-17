package com.form.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.form.entities.Query;
import com.form.repository.QueryRepository;

@Service
public class QueryService {

	@Autowired
	private QueryRepository queryRepository;
	
	public Query saveQuery(Query query) {
		return queryRepository.save(query);
	}
	
	public List<Query> getAllQueryByUserId(String userId){
		return queryRepository.findAllQueryByUserId(userId);
	}
	
	public Query getQueryByAction(String action) {
		return queryRepository.findQueryByAction(action);
	}
	
	public Query getQuerByReqId(String id) {
		return queryRepository.findQueryById(id);
	}
}
