package com.nse.transaction.service;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.nse.transaction.entity.WalletTransaction;
import com.nse.transaction.exception.NoDataInTransactionException;

// Test the methods using data from your table


@ExtendWith (SpringExtension.class)
@SpringBootTest
class WalletTransactionServiceTest {
	
	@Autowired
	IWalletTransactionService service;
	
	@Autowired
	WalletTransaction transaction=null;
	
	// Add Transaction
	@Test
	void testAddTransaction() {
		
		transaction.setAccountId(123L);
		transaction.setAmount(500D);
		transaction.setDescription("Transferring Rs 500");
		transaction.setReceiverId(124L);
		
		WalletTransaction updatedTransaction = service.addTransaction(transaction);
		
		Assertions.assertEquals(updatedTransaction.toString(), transaction.toString());
		
	}

	
	
	// Get Transaction By Id
	@Test
	void testGetTransactionById() {
		
		transaction.setTransactionId(129L);
		
		Assertions.assertEquals(transaction.getTransactionId(), service.getTransactionById(129L).getTransactionId());
		
		
	}

	
	// Transaction History
	@Test
	void testGetTransactionsByAccountId() {
		
		transaction.setTransactionId(128L);
		transaction.setAccountId(123L);
		transaction.setAccountBalance(3500D);
		transaction.setAmount(500D);
		transaction.setDateOfTransaction(Date.valueOf("2021-03-04"));
		transaction.setDescription("Transferring Rs 500");
		transaction.setReceiverId(124L);
		
		WalletTransaction transaction2 = new WalletTransaction();
		
		
		transaction2.setTransactionId(129L);
		transaction2.setAccountId(123L);
		transaction2.setAccountBalance(2500D);
		transaction2.setAmount(1000D);
		transaction2.setDateOfTransaction(Date.valueOf("2021-03-04"));
		transaction2.setDescription("Transferring Rs 1000");
		transaction2.setReceiverId(124L);
		
		
		List<WalletTransaction> listOfTransaction = new ArrayList<WalletTransaction>();
		
		listOfTransaction.add(transaction);
		listOfTransaction.add(transaction2);
		
		Assertions.assertEquals(listOfTransaction.toString(), service.getTransactionsByAccountId(123L).toString());
		
		
	}

	
	// AccountId in Account
	@Test
	void testGetAccountId() {
		
		List<Long> accountId = new ArrayList<Long>();
		
		accountId.add(123L);
		
		System.out.println(service.getAccountId(123L).get(0));
		
		Assertions.assertEquals(accountId.get(0), service.getAccountId(123L).get(0));
		
		
	}

	
	// Negative Test case
	@Test
	void testCountByAccountId() {
		
		
		transaction.setAccountId(125L);
		
		Assertions.assertEquals(0, service.countByAccountId(transaction.getAccountId()));
		
		
	}

}
