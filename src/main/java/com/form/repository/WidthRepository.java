package com.form.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.form.entities.Width;

@Repository
public interface WidthRepository extends JpaRepository<Width, Integer> {

	public Width findWidthByUserIdAndFormname(String userId,String formname);
	public List<Width> findAllWidthByUserId(String userId);
}
