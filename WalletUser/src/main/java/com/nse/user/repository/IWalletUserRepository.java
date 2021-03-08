package com.nse.user.repository;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nse.user.entity.WalletUser;


@Repository
public interface IWalletUserRepository extends JpaRepository<WalletUser, Long> {


	// Updating email Id by User ID
	
	@Transactional
	@Modifying
	@Query (value = "update WalletUser set email = ?1 where userId = ?2", nativeQuery = false)
	
	void updateEmailById(String email, long userId); 
	
	
	// Updating Phone No by User ID
	
	@Transactional
	@Modifying
	@Query ("update WalletUser set phoneNo = ?1 where userId = ?2")
	
	void updatePhoneNoById(String phoneNo, long userId); 
	
	
	// Updating Password by User ID
	
	@Transactional
	@Modifying
	@Query ("update WalletUser set password = ?1 where userId = ?2")
	
	void updatePasswordById(String password, long userId); 
	
	
	
	
	// Find By Email
	
	WalletUser findByEmail (String email);
	
	
	// Find By Phone No
	
	WalletUser findByPhoneNo (String phoneNo);
	
	
	// Find By Passsword
	
	WalletUser findByPassword (String password);
	
	
	// Find By Username
	
	WalletUser findByUserName (String userName);


	
}
