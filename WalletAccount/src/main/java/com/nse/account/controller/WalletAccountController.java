package com.nse.account.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nse.account.entity.WalletAccount;
import com.nse.account.enumeration.Status;
import com.nse.account.exception.AccountIdExistsInUserException;
import com.nse.account.exception.InputNotMatchingException;
import com.nse.account.exception.NoSuchIdException;
import com.nse.account.exception.NoSuchUserIdException;
import com.nse.account.exception.NoTransactions;
import com.nse.account.exception.NotEnoughAccountBalance;
import com.nse.account.exception.UserIdAlreadyExists;
import com.nse.account.service.IWalletAccountService;
import com.nse.account.vo.WalletTransaction;

@RestController
@RequestMapping("/account")
public class WalletAccountController {

	
	@Autowired
	IWalletAccountService service;
	
	
	@PostMapping(value = "/add-account", produces = "application/json")
	public WalletAccount addAccount (@Valid @RequestBody WalletAccount account) throws NotEnoughAccountBalance, NoSuchUserIdException, UserIdAlreadyExists, AccountIdExistsInUserException {
	
		
		
		//If condition to add userId != null condition
		if (service.findUserId(account.getUserId()).isEmpty() ) {
			
			throw new NoSuchUserIdException("The given User Id doesn't exists");
			
		}
		
		
		// Checking if account ID exists for the user ID in User
		
		if (service.getAccountIdFromUser(account.getUserId()).get(0) != 0L) {
			
			
			throw new AccountIdExistsInUserException("Account ID exists for the user");
			
		}
		
		// for balance > minBal && userId exists
		if (account.getAccountBalance() < IConstant.MINIMUM_BALANCE) {
			
			throw new NotEnoughAccountBalance(IConstant.MINIMUM_BALANCE + " is the minimum balance.");
			
		}
		
		
		else {
			
			// Changing the status 
			account.setStatus(Status.ACTIVE);
			
			WalletAccount addAccount = service.addAccount(account);
			
			
			// If user id is present in User then change the account id in user
			service.updateAccountIdInUser(addAccount.getAccountId(), account.getUserId());
			
			
			
			return addAccount;
		}
		
		
		
	}
	
	// Check if UserId exists in Account
	@GetMapping(value = "/get-user-id/{userId}", produces = "application/json")
	public WalletAccount getUserId (@Valid @PathVariable("userId") long userId) throws UserIdAlreadyExists {
		
		WalletAccount checkUserId = service.getUserById(userId);
		
		
		if (checkUserId == null) {
			
			throw new UserIdAlreadyExists("The User ID exists without Account");
		}
		
		else {
			
			
			return checkUserId;
		}
		
		
	}
	
	
	
	@PutMapping(value = "/update-account-balance/{id}", produces = "application/json")
	public ResponseEntity<String> updateAccountBalanceById (@Valid @PathVariable("id") long accountId, @RequestBody WalletAccount account) throws NoSuchIdException, NoSuchUserIdException, InputNotMatchingException {
		
		WalletAccount checkId = service.getAccountById(accountId);
		
		
		if (checkId == null) {
			
			throw new NoSuchIdException("The Account Id is not present");
			
		}
		
		
		// Checking for URL mismatch
		if (accountId != account.getAccountId()) {
			
			
			throw new InputNotMatchingException("IDs in URL do not match");
		}
		
		
		
		else {
			
			// Taking the accountBalance from DB and RequestBody data
			double totalAmount = checkId.getAccountBalance() + account.getAccountBalance();
			
			// Set it to the Request Body balance var
			//account.setAccountBalance(totalAmount);
			
			
			// update accountBalance 
			
			 service.updateAccountBalance(totalAmount, accountId);
			 
			 return new ResponseEntity<String> ("Balance is successfully updated", HttpStatus.ACCEPTED);
			 
		}
		
	}
	
	
	@GetMapping(value = "/get-account-balance/{id}", produces = "application/json")
	public ResponseEntity<String> getAccountBalanceById (@PathVariable("id") long accountId) throws NoSuchIdException {
		
		WalletAccount checkId = service.getAccountBalanceById(accountId);
		
		if (checkId == null) {
			
			throw new NoSuchIdException("The Account Id is not present");
			
		}
		
		else {
			
			
			return new ResponseEntity<String> ("The Account Balance = " +service.getAccountBalanceById(accountId).getAccountBalance(), HttpStatus.ACCEPTED);
		}
		
		
	}
	
	
	@GetMapping(value = "/get-account-id/{id}", produces = "application/json")
	public WalletAccount getAccountId (@PathVariable("id") long accountId) throws NoSuchIdException {
		
		WalletAccount checkId = service.getAccountById(accountId);
		
		if (checkId == null) {
			
			throw new NoSuchIdException("The Account Id is not present");
			
		}
		
		else {
			
			return checkId;
			
		}
		
		
	}
	
	
	
	@GetMapping(value = "/get-trans-history/{id}", produces = "application/json")
	public List<WalletTransaction> getTransacationHistory(@PathVariable("id") long accountId) throws NoSuchIdException, NoTransactions {
		
		
		//check accountID
		WalletAccount checkId = service.getAccountBalanceById(accountId);
		
		if (checkId == null) {
			
			throw new NoSuchIdException("The Account Id is not present");
			
		}
		
		
		if (service.countTransactionByAccountId(accountId).get(0) == 0) {
			
			throw new NoTransactions("No Transactions by the Account Holder");
			
		}
		
		

		else {
			
			List<WalletTransaction> getAllTransactions = service.getTransacationHistory(accountId);
			
			return getAllTransactions;
		}
		
		
	}
	
	
	
}
