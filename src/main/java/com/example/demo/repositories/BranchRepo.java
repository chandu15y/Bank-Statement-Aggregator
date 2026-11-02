package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Branch;

@Repository
public interface BranchRepo extends JpaRepository<Branch, Long> {
	List<Branch> findByCompanyCompanyId(Long companyId);
}
