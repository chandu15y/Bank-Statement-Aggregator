package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Company;
import com.example.demo.service.CompanyService;

@RestController
@RequestMapping("/company")
public class CompanyController {

	@Autowired
	private CompanyService companyService;

	@PostMapping("/create")
	public ResponseEntity<String> createComapny(@RequestBody Company company) {
//	ResponseEntity	gives whole data HTTP response.
		String result = companyService.createCompany(company);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getCompany(@PathVariable Long id)
//	<?> means it can hold any data (String, int, et....)
	{
		Company result = companyService.getCompany(id);
		if (result == null) {
			return new ResponseEntity<>("company not found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);

	}

	@GetMapping("/all")
	public ResponseEntity<List<Company>> getAllCompanies() {
		List<Company> allCompanies = companyService.getAllCompanies();

		return new ResponseEntity<>(allCompanies, HttpStatus.OK);

	}

}
