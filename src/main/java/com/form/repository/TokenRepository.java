package com.form.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.form.entities.Token;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface TokenRepository extends JpaRepository<Token, Integer> {

	public void deleteTokenByTokenId(String id);
	public Token findTokenByUserId(String userId);
}
