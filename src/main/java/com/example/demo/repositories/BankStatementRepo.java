package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.BankStatement;

public interface BankStatementRepo extends JpaRepository<BankStatement, Long>{

}
