package com.RideApp.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.RideApp.dto.WalletTransactionDto;
import com.RideApp.entities.WalletTransaction;
import com.RideApp.repositories.WalletTransactionRepository;
import com.RideApp.services.WalletTransactionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WalletTrasnactionServiceImpl implements WalletTransactionService {
	
	private final WalletTransactionRepository walletTrasnactionRepository;
	private final ModelMapper modelMapper;
	
	@Override
	public void creatNewWalletTransaction(WalletTransaction walletTransaction) {
		//WalletTransaction walletTransac = modelMapper.map(walletTransaction, WalletTransaction.class);
		walletTrasnactionRepository.save(walletTransaction);
	}//27/09/2024

}
