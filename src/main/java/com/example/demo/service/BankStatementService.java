package com.example.demo.service;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.BankStatement;
import com.example.demo.model.Branch;
import com.example.demo.model.Company;
import com.example.demo.model.Transaction;
import com.example.demo.model.User;
import com.example.demo.repositories.BankStatementRepo;
import com.example.demo.repositories.BranchRepo;
import com.example.demo.repositories.CompanyRepo;
import com.example.demo.repositories.TransactionRepo;
import com.example.demo.repositories.UserRepo;
import com.example.demo.utils.DummyDataGenerator;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

@Service
public class BankStatementService {

	private static final String STATEMENTS_DIR = "C:/Users/HP/OneDrive/Desktop/JFS/Java Project/BSA/Generated Transactions";
	@Autowired
	private BankStatementRepo bankStatementRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private BranchRepo branchRepo;

	@Autowired
	private CompanyRepo companyRepo;

	@Autowired
	private AWSService awsService;

	@Autowired
	private TransactionRepo transactionRepo;

	public String generateStatement(Long userId, Long companyId, Long branchId, int transactionCount,
			boolean deleteAfterUpload) throws IOException {
		User user = userRepo.findById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
		Company company = companyRepo.findById(branchId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Company ID"));
		Branch branch = branchRepo.findById(branchId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Branch ID"));

		List<Transaction> transactions = DummyDataGenerator.generateDummyTransaction(transactionCount,
				company.getCompanyName(), branch.getBranchName());
//		creating a file name of .csv file extension
		String fileName = "company_" + companyId + "_user_" + userId + "_" + System.currentTimeMillis() + ".csv";
//		saving the file in the mentioned path
		String filePath = STATEMENTS_DIR + "/" + fileName;

//		print writer helps to write the data in output stream
		try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
			writer.println("transaction_id,date,amount,description,company_name");
//			we want to print these data in output stream
			for (Transaction temp : transactions) {
				writer.printf("%s,%s,%.2f,%s,%s%n", temp.getTransactionId(), temp.getDate(), temp.getAmount(),
						temp.getDescription(), temp.getCompanyName());
			}
		}

		String fileURL = awsService.uploadFile(filePath, fileName);

		BankStatement bankStatement = new BankStatement();
		bankStatement.setUser(user);
		bankStatement.setBranch(branch);
		bankStatement.setCompany(company);
		bankStatement.setStatementDate(new Date());
		bankStatement.setStatementData(fileURL);

		bankStatementRepo.save(bankStatement);

//		if we want to delete the file then 
		if (deleteAfterUpload) {
			new File(filePath).delete();
		}
		return fileURL;

	}

	public void parseStatement(String filePath) throws CsvException, ParseException {
		try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
			List<String[]> records = reader.readAll();
			for (String[] record : records.subList(1, records.size()))// skipping the header row
			{
				Transaction transaction = new Transaction();
				transaction.setTransactionId(record[0]);
				transaction.setDate(new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy").parse(record[1]));
				transaction.setAmount(Double.parseDouble(record[2]));
				transaction.setDescription(record[3]);
				transaction.setCompanyName(record[4]);
				transactionRepo.save(transaction);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
