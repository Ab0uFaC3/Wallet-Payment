package com.nse.transaction.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nse.transaction.entity.WalletTransaction;


@Repository
public interface IWalletTransactionRepository extends JpaRepository<WalletTransaction, Long> {
	
	
	// To check whether the table is empty
	
	long countByAccountId(long accountId);

	
	// To get the Transaction Histroy for accountId
	@Query ("from WalletTransaction where accountId = ?1")
	
	List<WalletTransaction> findTransactionHistory(long accountId);
	
	
	// To check whether the account Id exists in WalletAccount class
	@Query (value = "select account_id from wallet_account where account_id = ?1", nativeQuery = true)
	
	List<Long> findAccountId(long accountId);
	
	
	// account balance by id from account
	
	@Query (value = "select account_balance from wallet_account where account_id = ?1", nativeQuery = true)
	
	List<Double> findAccountBalance(long accountId);
 	
	
	// update receiver balance in account
	@Transactional
	@Modifying
	@Query (value = "update wallet_account set account_balance = ?1 where account_id = ?2", nativeQuery = true)
	
	void updateReceiverBalance(double accountBalance ,long receiverId);
	
	
	// update accountId balance in account
	@Transactional
	@Modifying
	@Query (value = "update wallet_account set account_balance = ?1 where account_id = ?2", nativeQuery = true)
	
	void updateAccountBalanceById(double accountBalance ,long accountId);
	
	
	
	// update accountId balance in transaction
	@Transactional
	@Modifying
	@Query (value = "update wallet_transaction set account_balance = ?1 where transaction_id = ?2", nativeQuery = true)
	
	void updateAccountBalance(double accountBalance ,long transaction_id);
	
	
	
}
