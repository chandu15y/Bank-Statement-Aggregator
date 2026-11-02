package com.example.demo.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.AWSService;
import com.example.demo.service.BankStatementService;

@RestController
@RequestMapping("/statements")
public class BankStatementController {

	@Autowired
	private BankStatementService bankStatementService;

	@Autowired
	private AWSService awsService;

	@PostMapping("/generate")
	public String generateStatement(@RequestParam Long userId, @RequestParam Long companyId,
			@RequestParam Long branchId, @RequestParam int transactionCount, @RequestParam boolean deleteAfterUpload) {
		try {
			return bankStatementService.generateStatement(userId, companyId, branchId, transactionCount,
					deleteAfterUpload);
		} catch (IOException e) {
			return "Error generating bank statement: " + e.getMessage();
		}

	}

	@GetMapping("/download")
	public String downloadStatement(@RequestParam String key) {
		try {
			awsService.downloadStatement(key);
			return "File downloaded successfully to your Downloads folder.";
		} catch (IOException e) {
			return "Error downloading bank statement: " + e.getMessage();
		}
	}

	@PostMapping("/parse")
	public String parseStatement(String filePath)
//    to make the csv file to java object 
	{
		try {
			bankStatementService.parseStatement(filePath);
			return "CSV File got parsed successfully and saved";
		} catch (Exception e) {
			return "Error Occurred while parsing the file: " + e.getMessage();
		}

	}

}
