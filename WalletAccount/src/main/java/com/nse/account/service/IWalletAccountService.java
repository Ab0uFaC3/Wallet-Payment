package com.nse.account.service;

import java.util.List;

import com.nse.account.entity.WalletAccount;
import com.nse.account.vo.WalletTransaction;

public interface IWalletAccountService {
	
	
	public WalletAccount addAccount (WalletAccount account);
	
	
	public WalletAccount getAccountBalanceById(long accountId);

	
	public WalletAccount getAccountById(long accountId);
	
	
	public List<WalletTransaction> getTransacationHistory(long accountId);

	
	// Checking whether the transaction table is empty
	public List<Integer> countTransactionByAccountId(long accountId);
	
	
	// Check for the user id in User 
	public List<Long> findUserId(long userId);
	
	
	// Update the account id in User
	public void updateAccountIdInUser(long accountId, long userId);
	
	
	// Check user id in Account
	public WalletAccount getUserById (long userId);
	
	
	// update account balance
	public void updateAccountBalance (double accountBalance, long accountId);
	
	
	// Checking whether account id exists in User
	public List<Long> getAccountIdFromUser (long userId);
	
	
	
}
