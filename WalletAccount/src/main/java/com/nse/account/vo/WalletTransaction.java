package com.nse.account.vo;

import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WalletTransaction {
	
	private long transactionId;
	
	private long receiverId;
	private String description;
	
	private Date dateOfTransaction = new Date(System.currentTimeMillis());

	private double amount;
	private double accountBalance;
	
	private long accountId;
	
	

}
