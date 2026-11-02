package com.example.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Company;

public interface CompanyRepo extends JpaRepository<Company, Long> {
	Optional<Company> findByCompanyName(String companyName);
}
