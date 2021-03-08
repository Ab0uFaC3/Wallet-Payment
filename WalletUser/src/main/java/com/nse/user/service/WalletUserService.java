package com.nse.user.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.nse.user.entity.WalletUser;
import com.nse.user.repository.IWalletUserRepository;
import com.nse.user.vo.ResponseTemplate;
import com.nse.user.vo.WalletAccount;

@Service
public class WalletUserService implements IWalletUserService {
	
	
	@Autowired
	IWalletUserRepository repo;
	
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	
	ResponseTemplate response = new ResponseTemplate();
	
	
	// Adding the user
	public WalletUser addUser(WalletUser user) {
		
		return repo.save(user);
	}
	
	// Updating password by Id
	public void updatePasswordById (String password, long userId) {
		
		
		repo.updatePasswordById(password, userId);
	}
	
	
	// Updating Phone number
	public void updatePhoneNoById (String phoneNo, long userId) {
		
		repo.updatePhoneNoById(phoneNo, userId);
	}
	
	
	
	public void updateEmailById(String email, long userId) {
		
		repo.updateEmailById(email, userId);
	}
	
	
	
	// Find user Id 
	public WalletUser getUserById (long userId) {
		
		
		return repo.findById(userId).orElse(null);
	}
	
	
	// Find email
	public WalletUser getByEmail (String email) {
		
		return repo.findByEmail(email);
	}
	
	
	// Find by Phone no
	public WalletUser findByPhoneNo (String phoneNo) {
		
		return repo.findByPhoneNo(phoneNo);
	}
	
	
	// Find By Passsword
	
	public WalletUser findByPassword (String password) {
		
		
		
		return repo.findByPassword(password);
	}
	
	
	// Find By Username
	
	public WalletUser findByUserName (String userName) {
		
		
		
		return repo.findByUserName(userName);
	}
	
	

	// Response Template to fetch both User and Account
	public ResponseTemplate getUserWithAccount (long userId) {
		
		
		
		WalletUser user = repo.findById(userId).orElse(null);
		
		WalletAccount account = webClientBuilder.build()
				.get()
				.uri("http://WalletAccount/account/get-account-id/"+user.getAccountId())
				.retrieve()
				.bodyToMono(WalletAccount.class)
				.block();
		
		
		response.setAccount(account);
		response.setUser(user);
		
		return response;
		
	}
	


}
