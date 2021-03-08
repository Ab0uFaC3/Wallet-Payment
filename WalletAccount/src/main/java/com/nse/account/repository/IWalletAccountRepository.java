package com.nse.account.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nse.account.entity.WalletAccount;


@Repository
public interface IWalletAccountRepository extends JpaRepository<WalletAccount, Long> {
	
	@Transactional
	@Modifying
	@Query (value = "select count(*) from wallet_transaction where account_id = ?1", nativeQuery = true)
	
	List<Integer> countTransactionByAccountId(long accountId);
	
	
	// to check whether the user Id is available or not
	
	@Transactional
	@Modifying
	@Query (value = "select user_id from wallet_user where user_id = ?1", nativeQuery = true)
	
	List<Long> findUserId(long userId);
	
	
	// Updating account id in User
	
	@Transactional
	@Modifying
	@Query (value = "update wallet_user set account_id = ?1 where user_id = ?2", nativeQuery = true)
	
	void updateAccountIdInUser(long accountId, long userId);
	

	
	// Check if userId is present in Account
	
	WalletAccount findByUserId (long userId);
	
	
	
	// update account balance
	
	@Transactional
	@Modifying
	@Query (value = "update WalletAccount set accountBalance = ?1 where accountId = ?2")
	
	void updateAccountBalance (double accountBalance, long accountId);
	
	
	// Checking accountId present in User
	
	@Transactional
	@Modifying
	@Query (value = "select account_id from wallet_user where user_id = ?1", nativeQuery = true)
	
	List<Long> getAccountIdFromUser (long userId);
	
	
}
