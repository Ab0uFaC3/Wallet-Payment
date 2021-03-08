package com.nse.user.service;

import java.util.List;

import com.nse.user.entity.WalletUser;
import com.nse.user.vo.ResponseTemplate;

public interface IWalletUserService {
	
	// Add user
	public WalletUser addUser(WalletUser user);
	
	// Update password by User id
	public void updatePasswordById (String password, long userId);
	
	
	// Update phone No by User id
	public void updatePhoneNoById (String phoneNo, long userId);
	
	
	// Update Email
	public void updateEmailById (String email, long userId);
		
	
	
	// Find user id 
	public WalletUser getUserById (long userId);
	
	
	
	// Find by email
	public WalletUser getByEmail (String email);
	
	
	// Find by Phone no
	public WalletUser findByPhoneNo (String phoneNo);
	
	
	// Find By Password
	
	public WalletUser findByPassword (String password);
	
	
	// Find By Username
	
	public WalletUser findByUserName (String userName);
	
	
	// Get both User and Account details
	public ResponseTemplate getUserWithAccount (long userId);
	
	
	

}
