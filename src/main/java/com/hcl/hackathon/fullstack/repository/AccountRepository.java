package com.hcl.hackathon.fullstack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.hackathon.fullstack.model.Account;

public interface AccountRepository extends JpaRepository<Account, String> {
  
  public Account findByUsername(String username);

}