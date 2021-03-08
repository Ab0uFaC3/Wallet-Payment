package com.nse.user.service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.nse.user.entity.WalletUser;

// Test the methods using data from your table

@ExtendWith (SpringExtension.class)
@SpringBootTest
class WalletUserServiceTest {
	
	@Autowired
	WalletUser user;
	
	
	@Autowired
	IWalletUserService service;

	
	
	@Test
	void testAddUser() {
		
		user.setUserId(131L);;
		user.setEmail("barbie123@gmail.com");
		user.setUserName("Barbie Huo");
		user.setPassword("Barb1e_H@O");
		user.setPhoneNo("7281029483");
		
		
		Assertions.assertEquals(user.toString(), service.addUser(user).toString());
		

	}


	@Test
	void testUpdateEmailById() {
		
		user.setEmail("min.nam@outlook.com");
		user.setUserId(121L);
		
		service.updateEmailById(user.getEmail(), user.getUserId());
		
		
		Assertions.assertEquals(user.getEmail(), service.getUserById(user.getUserId()).getEmail());
		
		
	}

	
	// Negative Testing
	@Test
	void testGetUserById() {
	
		
		Assertions.assertNull(service.getUserById(140L), "The User Id doesn't exists");
	
		
		
	}


	@Test
	void testFindByPhoneNo() {
		
		user.setUserId(120L);
		user.setEmail("susie.pitts@rediffmail.com");
		user.setUserName("Susie Pitts");
		user.setPassword("P1!t$123");
		user.setPhoneNo("8019372814");
		user.setAccountId(123L);
		
		
		Assertions.assertEquals(user.toString(), service.findByPhoneNo(user.getPhoneNo()).toString());
		
		
		
	}

	
	
	@Test
	void testFindByPassword() {
		

		user.setUserId(119L);
		user.setEmail("helly.shetty@gmail.com");
		user.setUserName("Helly Shetty");
		user.setPassword("He1ly$hettY");
		user.setPhoneNo("8291038291");
		user.setAccountId(122L);
		
		
		Assertions.assertEquals(user.toString(), service.findByPassword(user.getPassword()).toString());
		
		
		
	}

	
	
	@Test
	void testFindByUserName() {
		
		
		user.setUserId(127L);
		user.setEmail("henry@hotmail.com");
		user.setUserName("Henry Lau");
		user.setPassword("HeNrY_L@0");
		user.setPhoneNo("8293820192");
		user.setAccountId(0L);
		
		
		Assertions.assertEquals(user.toString(), service.findByUserName(user.getUserName()).toString());
		
		
		
	}

}
