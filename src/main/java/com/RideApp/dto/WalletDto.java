package com.RideApp.dto;

import java.util.List;

import com.RideApp.entities.WalletTransaction;

import lombok.Data;
@Data
public class WalletDto {

	private Long id;

    private UserDto user;

    private Double balance;

    private List<WalletTransaction> transactions;
	
	
}
