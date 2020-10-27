package edu.cetys.springlabs.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tokens", schema="wine-tour-planner")
public class Token {

	
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "token", columnDefinition = "VARCHAR(100)") //, columnDefinition = "BINARY(16)
	private String token;
	
	@Column(name="user_id", nullable=false)
	private int userId;
	
	@Column(name="expiration_time", nullable=false)
	private Date expirationTime;
	
	@Column(name="active", nullable=false)
	private boolean active;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Date getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(Date expirationTime) {
		this.expirationTime = expirationTime;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
}
