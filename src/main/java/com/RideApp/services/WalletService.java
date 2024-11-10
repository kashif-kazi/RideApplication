package com.RideApp.services;

import com.RideApp.entities.Ride;
import com.RideApp.entities.User;
import com.RideApp.entities.Wallet;
import com.RideApp.enums.TransactionMethod;

public interface WalletService {

	Wallet addMoneyToWallet(User user, Double amount, 
			String transactionId, Ride ride, TransactionMethod transactionMethod);

	Wallet deductMoneyFromWallet(User user, Double amount, 
			String transactionId, Ride ride, TransactionMethod transactionMethod);

	void withdrawAllMyMoneyFromWallet(Long userId, Double amount);

	Wallet findWalletById(Long walletId);

	Wallet creatNewWallet(User user);

	Wallet findByUser(User user);

}
