package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Company;
import com.example.demo.model.User;
import com.example.demo.repositories.CompanyRepo;
import com.example.demo.repositories.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CompanyRepo companyRepo;

	public String registerUser(User user) {
//		checking the company information is given or not
		if (user.getCompany() == null || user.getCompany().getCompanyId() == null) {
			System.out.println("Company information is mandatory");
			return "Company information is mandatory";
		}

//		checking the given company is existing company or not
		Company company = companyRepo.findById(user.getCompany().getCompanyId()).orElse(null);
		if (company == null) {
			return "Company doesnt exits";
		}

//		checking the user is already present or not
		if (userRepo.findByUsername(user.getUsername()) != null) {
			return "User already exists with username : " + user.getUsername();
		}
		user.setCompany(company);
		user.setPassword(user.getPassword());
		userRepo.save(user);
		System.out.println("User registered successfully: " + user.getUsername());
		return "User registered successfully!";
	}

	public String loginUser(String username, String password) {
		User user = userRepo.findByUsername(username);
		if (user != null && (user.getPassword().equals(password))) {
			return "User : " + user.getUsername() + " loggedin Successfully";
		}
		return "Invalid Username or Password, Please try again";

	}

	public User getUser(Long id) {
		return userRepo.findById(id).orElse(null);

	}

	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

}
