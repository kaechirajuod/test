package com.hcl.hackathon.fullstack.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "ACCOUNT")
public class Account {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String id;
  
	@Column(name = "USERNAME", nullable = false)
  private String username;
	
	@Column(name = "PASSWORD", nullable = false)
  private String password;
  
  public Account(){}
  
  public Account(String username, String password) {
    this.username = username;
    this.password = password;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }

}
