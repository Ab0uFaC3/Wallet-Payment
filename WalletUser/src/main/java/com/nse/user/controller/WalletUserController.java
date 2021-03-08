package com.nse.user.controller;


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

import com.nse.user.entity.WalletUser;
import com.nse.user.exception.EmailAlreadyExistsException;
import com.nse.user.exception.InputNotMatchingException;
import com.nse.user.exception.NoSuchAccountIdException;
import com.nse.user.exception.NoSuchUserIdException;
import com.nse.user.exception.PasswordAlreadyExistsException;
import com.nse.user.exception.PhoneNoAlreadyExistsException;
import com.nse.user.exception.UserNameAlreadyExistsException;
import com.nse.user.service.IWalletUserService;
import com.nse.user.vo.ResponseTemplate;

@RestController
@RequestMapping("/user")
public class WalletUserController {

	
	@Autowired
	IWalletUserService service;
	
	// Adding user details
	@PostMapping(value = "/add-user", produces = "application/json")
	public WalletUser addUser(@Valid @RequestBody WalletUser user) throws EmailAlreadyExistsException, PhoneNoAlreadyExistsException, PasswordAlreadyExistsException, UserNameAlreadyExistsException {
		
		
		// check whether phone no exists
		
		if (service.findByPhoneNo(user.getPhoneNo()) != null) {
			
			
			throw new PhoneNoAlreadyExistsException("The Phone Number already exists");
		}
		
		
		// check whether password exists
		if (service.findByPassword(user.getPassword()) != null) {
			
			
			throw new PasswordAlreadyExistsException("The Password already exists");
		}
		
		
		// check whether username exists
		
		if (service.findByUserName(user.getUserName()) != null) {
			
			
			throw new UserNameAlreadyExistsException("The User Name already exists");
		}
		
		
		
		// check whether email exists

		if (service.getByEmail(user.getEmail()) != null) {
			
			
			throw new EmailAlreadyExistsException("The email id already exists");
		}
		
		else {
			
			return service.addUser(user);
			
		}
		
		
		
	}
	
	
	// Find if User id is present in User Table
	@GetMapping(value = "/get-user-id/{id}", produces = "application/json")
	public WalletUser getUserById (@PathVariable("id") long userId) throws NoSuchUserIdException {
		
		WalletUser checkUserId = service.getUserById(userId);
		
		if (checkUserId == null ) {
			
			throw new NoSuchUserIdException("The User Id doesn't exists");
			
		}
		
		else {
			
			return checkUserId;
		}
		
		 
		
	}
	
	
	
	// updating Password by User Id
	@PutMapping(value = "/update-password/{id}", produces = "application/json")
	public ResponseEntity<String> updatePasswordById (@PathVariable("id") long userId, @Valid @RequestBody WalletUser user) throws NoSuchUserIdException, PasswordAlreadyExistsException, InputNotMatchingException {
		
		WalletUser checkUserId = service.getUserById(userId);
		
		if (checkUserId == null ) {
			
			throw new NoSuchUserIdException("The User Id doesn't exists");
			
		}
		
		
		// If userId != user.getUserId()
		if (userId != user.getUserId()) {
			
			throw new InputNotMatchingException("IDs in URL do not match");
		}
		
		
		// check whether password exists
		if (service.findByPassword(user.getPassword()) != null) {
			
			
			throw new PasswordAlreadyExistsException("The Password already exists");
		}
		
		
		else {
			
			service.updatePasswordById(user.getPassword(), userId);
			
			
			return new ResponseEntity<String> ("The Password is updated ", HttpStatus.ACCEPTED);
		}
		
	}
	
	
	// update Phone Number
	@PutMapping(value = "/update-phone-no/{id}", produces = "application/json")
	public ResponseEntity<String> updatePhoneNo (@PathVariable("id") long userId, @Valid @RequestBody WalletUser user) throws NoSuchUserIdException, PhoneNoAlreadyExistsException, InputNotMatchingException {
		
		WalletUser checkUserId = service.getUserById(userId);
		
		if (checkUserId == null) {
			
			throw new NoSuchUserIdException("The User Id doesn't exists");
			
		}
		
		// If userId != user.getUserId()
		if (userId != user.getUserId()) {
			
			throw new InputNotMatchingException("IDs in URL do not match");
		}
		
		
		// check whether phone no exists
		
		if (service.findByPhoneNo(user.getPhoneNo()) != null) {
			
			
			throw new PhoneNoAlreadyExistsException("The Phone Number already exists");
		}
		
		
		
		else {
			
			service.updatePhoneNoById(user.getPhoneNo(), userId);
			
			return new ResponseEntity<String> ("The Phone No is updated ", HttpStatus.ACCEPTED);
		}
		
	}
	
	
	
	@PutMapping(value = "/update-email/{id}", produces = "application/json")
	public ResponseEntity<String> updateEmailById (@PathVariable("id") long userId, @Valid @RequestBody WalletUser user) throws NoSuchUserIdException, EmailAlreadyExistsException, InputNotMatchingException {
		
		WalletUser checkUserId = service.getUserById(userId);
		
		if (checkUserId == null) {
			
			throw new NoSuchUserIdException("The User Id doesn't exists");
			
		}
		
		// If userId != user.getUserId()
		if (userId != user.getUserId()) {
			
			throw new InputNotMatchingException("IDs in URL do not match");
		}
		
		
		// check whether email exists

		if (service.getByEmail(user.getEmail()) != null) {
			
			
			throw new EmailAlreadyExistsException("The Email id already exists");
		}
		
		
		else {
			
			service.updateEmailById(user.getEmail(), userId);
			
			return new ResponseEntity<String> ("The email Id is updated ", HttpStatus.ACCEPTED);
			
			//return getUser;
		}
		
	}
	
	
	
	@GetMapping(value = "/get-user-account/{userId}", produces = "application/json")
	public ResponseTemplate getUserWithAccount (@PathVariable("userId") long userId) throws NoSuchUserIdException, NoSuchAccountIdException {
		
		WalletUser checkUserId = service.getUserById(userId);
		
		if (checkUserId == null ) {
			
			throw new NoSuchUserIdException("The User Id doesn't exists");
			
		}
		
		// Account Id exists in User
		if (checkUserId.getAccountId() == 0L) {
			

			throw new NoSuchAccountIdException("The Account Id doesn't exists ");
		}

		
		
		else {
			
			return service.getUserWithAccount(userId);
			
		}
		

		
	}

	
	
}
