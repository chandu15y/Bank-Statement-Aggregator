package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Company;
import com.example.demo.repositories.CompanyRepo;

@Service
public class CompanyService {

	@Autowired
	private CompanyRepo companyRepo;

	public String createCompany(Company company) {
		Optional<Company> existingCompany = companyRepo.findByCompanyName(company.getCompanyName());
		if (existingCompany.isPresent()) {
			return "Company with name " + company.getCompanyName()
					+ " is already exists please try with new company name";
		}
		companyRepo.save(company);
		return "Saves company : " + company.getCompanyName();

	}

	public Company getCompany(Long id) {
		return companyRepo.findById(id).orElse(null);

	}

	public List<Company> getAllCompanies() {
		return companyRepo.findAll();

	}

}
