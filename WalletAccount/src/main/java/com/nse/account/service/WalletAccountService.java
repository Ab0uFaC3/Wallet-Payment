package com.nse.account.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.nse.account.entity.WalletAccount;
import com.nse.account.repository.IWalletAccountRepository;
import com.nse.account.vo.WalletTransaction;

@Service
public class WalletAccountService implements IWalletAccountService {

	
	@Autowired
	IWalletAccountRepository repo;
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	
	public WalletAccount addAccount (WalletAccount account) {
		
		
		return repo.save(account);
		
	}
	
	
	// Check user id in Account
	public WalletAccount getUserById (long userId) {
		
		
		return repo.findByUserId(userId);
	}
	
	
	public WalletAccount getAccountBalanceById(long accountId) {
		
		return repo.findById(accountId).orElse(null);
	}
	
	
	public WalletAccount getAccountById(long accountId) {
		
		return repo.findById(accountId).orElse(null);
	}
	
	
	public List<Integer> countTransactionByAccountId(long accountId) {
		
		
		return repo.countTransactionByAccountId(accountId);
	}
	
	
	
	public List<Long> findUserId(long userId) {
		
		return repo.findUserId(userId);
	}
	
	
	
	
	public void updateAccountIdInUser(long accountId, long userId) {
		
		
		repo.updateAccountIdInUser(accountId, userId);
	}
	
	
	
	// update account balance
	public void updateAccountBalance (double accountBalance, long accountId) {
		
		
		repo.updateAccountBalance(accountBalance, accountId);
	}
	
	
	// Getting account id from User
	public List<Long> getAccountIdFromUser (long userId) {
		
		
		return repo.getAccountIdFromUser(userId);
	}
	
	
	
	
	
	public List<WalletTransaction> getTransacationHistory(long accountId) {
		
		
		//List<WalletTransaction> listOfTransactions = restTemplate.getForObject("http://localhost:8080/get-transaction-accountid/" +accountId, List.class);
		
		List<WalletTransaction> listOfTransactions = webClientBuilder.build()
										.get()
										.uri("http://WalletTransaction/transaction/get-transaction-accountid/" +accountId)
										.retrieve()
										.bodyToMono(List.class)
										.block();
		
		return listOfTransactions;
		
	}
	
	
	
	
	
	
	
}
