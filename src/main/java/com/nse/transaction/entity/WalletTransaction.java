package com.nse.transaction.entity;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Component
@Data
@NoArgsConstructor
public class WalletTransaction {

	
	@Id
	@GeneratedValue
	private long transactionId;
	
//	@NotNull
//	@Positive
	private Long receiverId;


	// Description will be between 5 to 50 letters
	@NotBlank
	@Size(min=5, max=50)
	private String description;
	
	
	@CreationTimestamp
	private Date dateOfTransaction = new Date(System.currentTimeMillis());
	
	
	// Amount should be min 500
	@NotNull
	@Min(500)
	@Max(10000)
	private Double amount;
	
	
	private double accountBalance;
	
	@NotNull
	@Positive
	private Long accountId;
	 
	
	
}
