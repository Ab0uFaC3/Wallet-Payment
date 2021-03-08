package com.nse.user.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Component
@Data
@NoArgsConstructor
public class WalletUser {
	
	@Id
	@GeneratedValue
	private long userId;
	
	@Pattern (regexp = "^[A-Z][a-zA-Z]+([\s][A-Z][a-zA-Z]+)+$")
	private String userName;
	
	@Pattern (regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$")
	private String password;
	
	
	@Pattern (regexp = "[7-9][0-9]{9}")
	private String phoneNo;
	
	@Email
	private String email;
	
	private long accountId = 0L;
	
	
}
