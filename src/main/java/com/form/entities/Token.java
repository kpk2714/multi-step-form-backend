package com.form.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Token {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String userId;
	private String password;
	private String tokenId;
	private Date saveTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTokenId() {
		return tokenId;
	}
	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}
	public Date getSaveTime() {
		return saveTime;
	}
	public void setSaveTime(Date saveTime) {
		this.saveTime = saveTime;
	}
	public Token(int id, String userId, String password, String tokenId, Date saveTime) {
		super();
		this.id = id;
		this.userId = userId;
		this.password = password;
		this.tokenId = tokenId;
		this.saveTime = saveTime;
	}
	public Token() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Token [id=" + id + ", userId=" + userId + ", password=" + password + ", tokenId=" + tokenId
				+ ", saveTime=" + saveTime + "]";
	}
	
	
	
	
}
