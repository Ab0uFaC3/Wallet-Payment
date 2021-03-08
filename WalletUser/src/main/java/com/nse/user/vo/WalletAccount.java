package com.nse.user.vo;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.nse.user.enumeration.Status;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WalletAccount {
	
	
	private long accountId;
	
	private double accountBalance;

	@Enumerated(EnumType.STRING)
	private Status status = Status.INACTIVE;
	
	private long userId;
	
	
}
