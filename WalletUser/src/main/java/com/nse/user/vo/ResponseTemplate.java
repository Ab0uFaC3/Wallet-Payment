package com.nse.user.vo;

import com.nse.user.entity.WalletUser;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseTemplate {
	
	
	private WalletUser user;
	private WalletAccount account;

}
