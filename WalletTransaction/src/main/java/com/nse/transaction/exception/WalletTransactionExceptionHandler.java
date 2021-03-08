package com.nse.transaction.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



@ControllerAdvice
public class WalletTransactionExceptionHandler {

	// Transaction Id Exception Handler
	@ExceptionHandler (NoSuchIdException.class)
	public ResponseEntity<String> getTransactionById(NoSuchIdException transactionId) {
		
		
		return new ResponseEntity<String> (transactionId.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	
	// Account Id Exception Handler
	@ExceptionHandler (NoSuchAccountIdException.class)
	public ResponseEntity<String> getAccountById(NoSuchAccountIdException accountId) {
		
		
		return new ResponseEntity<String> (accountId.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	
	// Receiver Id Exception Handler
	@ExceptionHandler (NoSuchReceiverIdException.class)
	public ResponseEntity<String> getReceiverById(NoSuchReceiverIdException receiverId) {
		
		
		return new ResponseEntity<String> (receiverId.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	
	// No Transaction Possible Exception Handler
	@ExceptionHandler (TransactionNotPossibleException.class)
	public ResponseEntity<String> checkTransactionStatus(TransactionNotPossibleException transaction) {
		
		
		return new ResponseEntity<String> (transaction.getMessage(), HttpStatus.BAD_REQUEST);
	}

	
	
	// No data in Transaction
	@ExceptionHandler (NoDataInTransactionException.class)
	public ResponseEntity<String> checkCountTransaction(NoDataInTransactionException transaction) {
		
		
		return new ResponseEntity<String> (transaction.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	
	
	// Checking if Account id is same as Receiver id
	
	@ExceptionHandler (AccountIdSameAsReceiverIdException.class)
	public ResponseEntity<String> checkAccountIdReceiverId(AccountIdSameAsReceiverIdException id) {
		
		
		return new ResponseEntity<String> (id.getMessage(), HttpStatus.BAD_REQUEST);
	}
	

	
}
