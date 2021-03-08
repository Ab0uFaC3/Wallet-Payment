package com.nse.transaction.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nse.transaction.entity.WalletTransaction;
import com.nse.transaction.repository.IWalletTransactionRepository;

@Service
public class WalletTransactionService implements IWalletTransactionService {
	
	
	@Autowired
	IWalletTransactionRepository repo;
	
	
	// Add transaction details
	public WalletTransaction addTransaction (WalletTransaction transaction) {
		
		return repo.save(transaction);
		
	}
	
	// Getting transaction details by Id
	public WalletTransaction getTransactionById (long transactionId) {
		
		return repo.findById(transactionId).orElse(null);
	}
	
	
	
	public List<WalletTransaction> getTransactionsByAccountId (long accountId) {
		
		
		return repo.findTransactionHistory(accountId);
	}
	
	
	// Checking accountId in Account 
	public List<Long> getAccountId (long accountId) {
		
		return repo.findAccountId(accountId);
	}
	
	
	public List<Double> findAccountBalance(long accountId) {
		
		return repo.findAccountBalance(accountId);
	}
	
	
	
	public void updateReceiverBalance(double accountBalance ,long receiverId) {
		
		repo.updateReceiverBalance(accountBalance ,receiverId);
	}
	
	
	public void updateAccountBalanceById(double accountBalance ,long accountId) {
		
		 repo.updateAccountBalanceById(accountBalance ,accountId);
	}
	
	
	public void updateAccountBalance(double accountBalance ,long transactionId) {
		
		 repo.updateAccountBalance(accountBalance ,transactionId);
	}
	
	
	
	
	public long countByAccountId(long accountId) {
		
		return repo.countByAccountId(accountId);
		
	}
	

}
