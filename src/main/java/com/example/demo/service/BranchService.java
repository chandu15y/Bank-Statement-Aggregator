package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Branch;
import com.example.demo.model.Company;
import com.example.demo.repositories.BranchRepo;
import com.example.demo.repositories.CompanyRepo;

@Service
public class BranchService {

	@Autowired
	private BranchRepo branchRepo;

	@Autowired
	private CompanyRepo companyRepo;

	public Branch createBranch(Branch branch) {
		Company company = companyRepo.findById(branch.getCompany().getCompanyId())
				.orElseThrow(() -> new RuntimeException("no company found"));
		branch.setCompany(company);
		return branchRepo.save(branch);

	}

	public Branch getBranch(Long id) {
		return branchRepo.findById(id).orElse(null);

	}

	public List<Branch> getCompantByCompanyId(Long id) {
		return branchRepo.findByCompanyCompanyId(id);
	}

}
