package com.nse.account.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.nse.account.entity.WalletAccount;
import com.nse.account.enumeration.Status;
import com.nse.account.exception.NoSuchUserIdException;

// Test the methods using data from your table

@ExtendWith (SpringExtension.class)
@SpringBootTest
class WalletAccountServiceTest {
	
	@Autowired
	WalletAccount account = null;
	
	@Autowired
	IWalletAccountService service;
	

	@Test
	void testAddAccount() {
		
		account.setAccountBalance(4000D);
		account.setUserId(127L);
		account.setStatus(Status.ACTIVE);
		
		Assertions.assertEquals(account.toString(), service.addAccount(account).toString());

		
	}

	
	// Getting account details from user Id in Account
	@Test
	void testGetUserById() {
		
		account.setAccountId(124L);
		account.setUserId(121L);
		account.setAccountBalance(5500D);
		account.setStatus(Status.ACTIVE);
		
		
		Assertions.assertEquals(account.toString(), service.getUserById(account.getUserId()).toString());
		
		
	}

	@Test
	void testGetAccountBalanceById() {
		
		account.setAccountId(123L);
		account.setAccountBalance(2500D);
		
		Assertions.assertEquals(account.getAccountBalance(), service.getAccountBalanceById(account.getAccountId()).getAccountBalance());
		
		
		
	}

	@Test
	void testGetAccountById() {
		
		account.setAccountId(122L);
		account.setUserId(119L);
		account.setAccountBalance(5500D);
		account.setStatus(Status.ACTIVE);
		
		
		Assertions.assertEquals(account.toString(), service.getAccountById(account.getAccountId()).toString());
		
		
	}

	
	// Negative Testing
	@Test
	void testFindUserId() {
		
		
		account.setUserId(130L);
		
		Assertions.assertEquals(true, service.findUserId(account.getUserId()).isEmpty());
		
		
		
	}

	@Test
	void testUpdateAccountBalance() {
		
		
		account.setAccountBalance(2500D);
		account.setAccountId(123L);
		
		
		service.updateAccountBalance(account.getAccountBalance(), account.getAccountId());
		
		Assertions.assertEquals(account.getAccountBalance(), service.getAccountBalanceById(account.getAccountId()).getAccountBalance());
		
		
	}


}
