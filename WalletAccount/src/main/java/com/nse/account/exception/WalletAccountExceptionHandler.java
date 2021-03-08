package com.nse.account.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class WalletAccountExceptionHandler {

	// No Account Id Exception Handler
	@ExceptionHandler (NoSuchIdException.class)
	public ResponseEntity<String> getAccountById(NoSuchIdException accountId) {
		
		
		return new ResponseEntity<String> (accountId.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	
	// Minimum Account Balance Exception Handler
	@ExceptionHandler (NotEnoughAccountBalance.class)
	public ResponseEntity<String> checkAccountBalance(NotEnoughAccountBalance balance) {
		
		
		return new ResponseEntity<String> (balance.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	
	// No Transactions by Account holder Exception Handler
	@ExceptionHandler (NoTransactions.class)
	public ResponseEntity<String> checkNoOfTransaction(NoTransactions transaction) {
		
		
		return new ResponseEntity<String> (transaction.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	
	// Checking the user id is present in User
	@ExceptionHandler (NoSuchUserIdException.class)
	public ResponseEntity<String> checkUserId(NoSuchUserIdException userId) {
		
		
		return new ResponseEntity<String> (userId.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	
	
	@ExceptionHandler (InputNotMatchingException.class)
	public ResponseEntity<String> checkInputMisMatch(InputNotMatchingException match) {
		
		
		return new ResponseEntity<String> (match.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	
	// User id already exists
	@ExceptionHandler (UserIdAlreadyExists.class)
	public ResponseEntity<String> checkUserId(UserIdAlreadyExists user) {
		
		
		return new ResponseEntity<String> (user.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	
	// Account id already exists in User
	@ExceptionHandler (AccountIdExistsInUserException.class)
	public ResponseEntity<String> checkAccountIdInUser(AccountIdExistsInUserException account) {
		
		
		return new ResponseEntity<String> (account.getMessage(), HttpStatus.BAD_REQUEST);
	}
	

	
}
