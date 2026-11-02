package com.example.demo.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.example.demo.model.Transaction;

public class DummyDataGenerator {

	private static final String ALPHANUMERIC_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private static final int TRANSACTION_ID_LENGTH = 8;
	private static final Random RANDOM = new Random();

	public static List<Transaction> generateDummyTransaction(int count, String companyName, String branchName) {
		List<Transaction> transactions = new ArrayList<>();
		for (int i = 0; i < count; i++) {
//			we are adding the item on list by creating the object for Transaction
			transactions.add(new Transaction(generateTransactionId(), // generates the transactionid
					new Date(), // generates date
					Math.random() * 1000, // multiplies some random number with 100 for amount
					"Transaction from " + branchName + " branch", // transaction description
					companyName)); // branch name
		}
		return transactions;

	}

	public static String generateTransactionId() {
//		to generate random transaction id for the dummy transactions 
		StringBuilder transactionId = new StringBuilder(TRANSACTION_ID_LENGTH);
		for (int i = 0; i < TRANSACTION_ID_LENGTH; i++) {
//			generating some random string with length of 8 characters 
			transactionId.append(ALPHANUMERIC_CHARACTERS.charAt(RANDOM.nextInt(ALPHANUMERIC_CHARACTERS.length())));
		}
		return transactionId.toString();

	}

}
