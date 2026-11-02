package com.example.demo.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

//final
@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	@Column(nullable = false, unique = true)
	private String username;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private String password;

	@ManyToOne
	@JoinColumn(name = "company_id", nullable = false)
//	here companyid in users table is foreign key referring to company id companyid in company table 
	private Company company;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	onetomany means one user entity can have many bankstatements objects
//	Here user is parent and bankstatement is child
//	lazy means the child records are fetched only when accessed.

	@JsonIgnore
//	When you put @JsonIgnore on a field or a method, that property will be skipped when converting your object to JSON (and also ignored while reading JSON into the object).
	private Set<BankStatement> bankStatements;

	public User() {
//		super();
	}

	public User(Long userId) {
//		super();
		this.userId = userId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Set<BankStatement> getBankStatements() {
		return bankStatements;
	}

	public void setBankStatements(Set<BankStatement> bankStatements) {
		this.bankStatements = bankStatements;
	}

}
