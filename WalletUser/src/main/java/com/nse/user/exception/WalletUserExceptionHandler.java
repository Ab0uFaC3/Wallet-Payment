package com.nse.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class WalletUserExceptionHandler {

	
	@ExceptionHandler
	public ResponseEntity<String> checkUserId (NoSuchUserIdException userId) {
		
		return new ResponseEntity<String> (userId.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	// Email already exists exception handler
	@ExceptionHandler
	public ResponseEntity<String> checkForEmail (EmailAlreadyExistsException email) {
		
		return new ResponseEntity<String> (email.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	
	
	// Phone No already exists exception handler
	@ExceptionHandler
	public ResponseEntity<String> checkForPhoneNo (PhoneNoAlreadyExistsException phoneNo) {
		
		return new ResponseEntity<String> (phoneNo.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	
	// Password already exists exception handler
	@ExceptionHandler
	public ResponseEntity<String> checkForPassword (PasswordAlreadyExistsException password) {
		
		return new ResponseEntity<String> (password.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	
	
	// Username already exists exception handler
	@ExceptionHandler
	public ResponseEntity<String> checkForUserName (UserNameAlreadyExistsException userName) {
		
		return new ResponseEntity<String> (userName.getMessage(), HttpStatus.BAD_REQUEST);
	}

	
	// TO check for accountId
	@ExceptionHandler
	public ResponseEntity<String> checkForAccountId (NoSuchAccountIdException accountId) {
		
		return new ResponseEntity<String> (accountId.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	// Input Mismatch exception handler
	@ExceptionHandler
	public ResponseEntity<String> checkForInputMisMatch (InputNotMatchingException match) {
		
		return new ResponseEntity<String> (match.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	
}
