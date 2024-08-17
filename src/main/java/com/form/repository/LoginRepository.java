package com.form.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.form.entities.User;

@Repository
public interface LoginRepository extends JpaRepository<User, Integer> {

	public User findUserByUsername(String username);
}
