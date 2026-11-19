package com.bhojbangla.auth;
import jakarta.persistence.*;
@Entity @Table(name="users") public class UserAccount{
@Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
@Column(nullable=false,unique=true,length=254) private String email;
@Column(name="password_hash",nullable=false) private String passwordHash;
@Column(nullable=false,length=24) private String role;
@Column(nullable=false) private boolean enabled=true;
protected UserAccount(){}
public UserAccount(String email,String passwordHash,String role){this.email=email;this.passwordHash=passwordHash;this.role=role;}
public Long getId(){return id;} public String getEmail(){return email;} public String getPasswordHash(){return passwordHash;} public String getRole(){return role;} public boolean isEnabled(){return enabled;}
}

