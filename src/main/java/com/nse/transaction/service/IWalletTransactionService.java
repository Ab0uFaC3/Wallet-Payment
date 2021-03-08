package com.nse.transaction.service;

import java.util.List;

import com.nse.transaction.entity.WalletTransaction;

public interface IWalletTransactionService {
	
	
	public WalletTransaction addTransaction (WalletTransaction transaction);
	
	public WalletTransaction getTransactionById (long transactionId);
	
	public List<WalletTransaction> getTransactionsByAccountId (long accountId);
	
	
	// Account id in Account
	public List<Long> getAccountId (long accountId);
	
	
	public List<Double> findAccountBalance(long accountId);
	
	// update receiver balance in account
	public void updateReceiverBalance(double accountBalance ,long receiverId);
	
	
	// Update account balance of account Id in account
	public void updateAccountBalanceById(double accountBalance ,long accountId);
	
	// Update account balance of account Id in transaction
	public void updateAccountBalance(double accountBalance ,long transactionalId);
	
	
	
	// To check whether the count is empty
	public long countByAccountId(long accountId);
	

}
