package com.nse.transaction.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nse.transaction.entity.WalletTransaction;
import com.nse.transaction.exception.AccountIdSameAsReceiverIdException;
import com.nse.transaction.exception.NoDataInTransactionException;
import com.nse.transaction.exception.NoSuchAccountIdException;
import com.nse.transaction.exception.NoSuchIdException;
import com.nse.transaction.exception.NoSuchReceiverIdException;
import com.nse.transaction.exception.TransactionNotPossibleException;
import com.nse.transaction.service.IWalletTransactionService;

@RestController
@RequestMapping("/transaction")
public class WalletTransactionController {

	
	@Autowired
	IWalletTransactionService service;
	
	
	// Add transaction details
	@PostMapping(value = "/add-transaction", produces = "application/json")
	public WalletTransaction addTransaction (@Valid @RequestBody WalletTransaction transaction) throws NoSuchAccountIdException, TransactionNotPossibleException, NoSuchReceiverIdException, AccountIdSameAsReceiverIdException {
		
		
		List<Double> checkAccountBalance;
		List<Double> checkReceiverBalance;	
		
		
		// Getting account Id from wallet account
		if (service.getAccountId(transaction.getAccountId()).isEmpty()) {
			
			
			throw new NoSuchAccountIdException("The Account Id doesn not exists");
		}
		
		// Getting receiver Id from wallet account
		if (service.getAccountId(transaction.getReceiverId()).isEmpty()) {
			
			
			throw new NoSuchReceiverIdException("The Receiver Id doesn not exists");
		}
		
		
		// Checking if account Id is same as receiver Id
		if (transaction.getAccountId() == transaction.getReceiverId()) {
		
			
			throw new AccountIdSameAsReceiverIdException("Account ID cannot be same as Receiver ID");
			
		}
		
		
		
		else {
			
			// Getting account Balance for account Id from wallet Account
			checkAccountBalance = service.findAccountBalance(service.getAccountId(transaction.getAccountId()).get(0));
			
			
			// Getting account Balance for receiver Id from wallet Account
			checkReceiverBalance = service.findAccountBalance(service.getAccountId(transaction.getReceiverId()).get(0));
			
			
		}
		
		
		if (checkAccountBalance.get(0) - transaction.getAmount() < IConstant.MINIMUM_BALANCE) {
			
			throw new TransactionNotPossibleException("The amount after transaction does not satisfy minimum balance clause");
		}
			
			
		else {
			
			// Amount after transaction
			double amountAfterTransaction = checkAccountBalance.get(0) - transaction.getAmount();
			
			
			// Amount to transfer for Receiver
			double amountAfterTransactionReceiver = checkReceiverBalance.get(0) + transaction.getAmount();
			

			// Display in Browser
			transaction.setAccountBalance(amountAfterTransaction);
			
			WalletTransaction addTransaction = service.addTransaction(transaction);
			
			
			
			// update accountId balance in transaction
			service.updateAccountBalance(amountAfterTransaction ,addTransaction.getTransactionId());
			
			
			
			// update accountId balance in account
			service.updateAccountBalanceById(amountAfterTransaction, service.getAccountId(transaction.getAccountId()).get(0));
			
			
			
			// update receiver balance in account
			service.updateReceiverBalance(amountAfterTransactionReceiver, service.getAccountId(transaction.getReceiverId()).get(0));
			
			
			
			return addTransaction;
		}
			
		
		
		
	}
	
	// get transaction id
	@GetMapping(value = "/get-transaction-id/{id}", produces = "application/json")
	public WalletTransaction getTransactionById (@PathVariable("id") long transactionId) throws NoSuchIdException {
		
		WalletTransaction checkId = service.getTransactionById(transactionId);
		
		if (checkId == null) {
			
			
			throw new NoSuchIdException("The entered Transaction Id is not available");
		}
		
		else {
			
			
			return checkId;
		}

	}
	
	
	
	// List of transactions
	@GetMapping(value = "/get-transaction-accountid/{id}", produces = "application/json")
	public List<WalletTransaction> getTransactionsByAccountId(@Valid @PathVariable("id") long accountId) throws NoSuchAccountIdException, NoDataInTransactionException {
		

		List<Long> checkAccountId = service.getAccountId(accountId);
		
		if (checkAccountId.isEmpty()) {
			
			
			throw new NoSuchAccountIdException("The Account Id is not present in Account");
		}
		
		
		if (service.countByAccountId(accountId) == 0) {
			
			throw new NoDataInTransactionException("No Transactions by the Account Holder");
		}
		
		
		else {
			
			List<WalletTransaction> getAllTransactions = service.getTransactionsByAccountId(accountId);
			
			return getAllTransactions;
		}
		

		
	}
	
	
	
}
