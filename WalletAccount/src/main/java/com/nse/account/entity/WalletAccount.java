package com.nse.account.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.stereotype.Component;

import com.nse.account.enumeration.Status;


import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Component
@Data
@NoArgsConstructor
public class WalletAccount {

	@Id
	@GeneratedValue
	private long accountId;
	
	@NotNull
	@Max(10000)
	private Double accountBalance;
	
	@Enumerated(EnumType.STRING)
	private Status status = Status.INACTIVE;
	
	@NotNull
	@Positive
	private long userId;
	
	
	
}
