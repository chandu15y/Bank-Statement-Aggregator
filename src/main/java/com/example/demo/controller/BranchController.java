package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Branch;
import com.example.demo.service.BranchService;

@RestController
@RequestMapping("/branches")
public class BranchController {

	@Autowired
	private BranchService branchService;

	@PostMapping("/create")
	public Branch createBranch(@RequestBody Branch branch) {
		return branchService.createBranch(branch);

	}

	@GetMapping("/{id}")
	public Branch getBranchById(@PathVariable Long id) {
		return branchService.getBranch(id);

	}

	@GetMapping("/company/{id}")
	public List<Branch> getCompanyByCompanyId(@PathVariable Long id) {
		return branchService.getCompantByCompanyId(id);
	}

}
