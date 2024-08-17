package com.form.service;

//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.form.entities.User;
import com.form.repository.LoginRepository;

@Service
public class LoginService {
	
	@Autowired
	private LoginRepository loginRepository;

	public User authenticatedUser(String username) throws Exception {
//		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/registration_portal","root","Mitra@123");
//		
//		String query = "select * from user where username = ?";
//		
//		PreparedStatement pst = con.prepareStatement(query);
//		pst.setString(1,username);
//		
//		ResultSet rs = pst.executeQuery();
//		
//		if(rs==null) {
//			throw new Exception("Invalid Username !!!");
//		}
//		
//		rs.next();
//		return new User(rs.getString(24),rs.getString(12));
		
		User user = loginRepository.findUserByUsername(username);
		if(user==null) {
			throw new Exception("Invalid Username !!!");
		}
		return new User(user.getUsername(),user.getPassword());
	}
	
	public boolean verifyUser(String username , String password) throws Exception {
//		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/registration_portal","root","Mitra@123");
//		
//		String query = "select * from user where username = ?";
//		
//		PreparedStatement pst = con.prepareStatement(query);
//		pst.setString(1,username);
//		
//		ResultSet rs = pst.executeQuery();
//		
//		if(rs==null) {
//			throw new Exception("Invalid Username !!!");
//		}
//		
//		rs.next();
		
		User user = loginRepository.findUserByUsername(username);
		
		if(user==null) {
			throw new Exception("Invalid Username !!!");
		}
		
		String getPass = user.getPassword();
		
		if(password.equals(getPass)) {
			return true;
		}
		return false;
	}
	
	
}
