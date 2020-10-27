package edu.cetys.springlabs.model;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;

import org.hibernate.FetchMode;
import org.hibernate.annotations.Fetch;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "Users", schema="wine-tour-planner")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name="email", length=100, nullable=false, unique=true)
	private String email;
	
	@Column(name="name", length=100, nullable=false, unique=false)
	private String name;
	
	@Column(name="password", length=200, nullable=false, unique=false)
	private String password;
	
	@Column(name="role", length=50, nullable=false, unique=false)
	private String role;
	
	@Column(name="active", nullable=false)
	private boolean active;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "winery_id", referencedColumnName = "id")
    private Winery winery;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Winery getWinery() {
		return winery;
	}

	public void setWinery(Winery winery) {
		this.winery = winery;
	}
	
}
