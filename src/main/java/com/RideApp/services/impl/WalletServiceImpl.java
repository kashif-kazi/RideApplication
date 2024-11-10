package com.RideApp.services.impl;

import org.springframework.stereotype.Service;

import com.RideApp.entities.Ride;
import com.RideApp.entities.User;
import com.RideApp.entities.Wallet;
import com.RideApp.entities.WalletTransaction;
import com.RideApp.enums.TransactionMethod;
import com.RideApp.enums.TransactionType;
import com.RideApp.exceptions.ResourceNotFoundException;
import com.RideApp.repositories.WalletRepository;
import com.RideApp.services.WalletService;
import com.RideApp.services.WalletTransactionService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

	private final WalletRepository walletRepository;
	private final WalletTransactionService walletTransactionService;

	@Override
	@Transactional
	public Wallet addMoneyToWallet(User user, Double amount, 
			String transactionId, Ride ride, TransactionMethod transactionMethod) {
		/*
		 * Wallet wallet = walletRepository.findByUser(user) .orElseThrow(() -> new
		 * ResourceNotFoundException(" user not found  with id" + user.getId()) );
		 * wallet.setBalance(wallet.getBalance()+amount);
		 */
		Wallet wallet = findByUser(user);
		wallet.setBalance(wallet.getBalance() + amount);
		
		WalletTransaction walletTransaction = WalletTransaction
				.builder() 
		.transactionId(transactionId)
		.ride(ride)
		.amount(amount)
		.transactionType(TransactionType.CREDIT)
		.transactionMethod(transactionMethod)
		.build();
		
		walletTransactionService.creatNewWalletTransaction(walletTransaction);
		
		
		return walletRepository.save(wallet);
	}

	@Override
	@Transactional
	public Wallet deductMoneyFromWallet(User user, Double amount, 
			String transactionId, Ride ride, TransactionMethod transactionMethod) {
		
		Wallet wallet = findByUser(user);
		wallet.setBalance(wallet.getBalance() - amount);
		
		WalletTransaction walletTransaction = WalletTransaction.builder()
				.transactionId(transactionId)
				.ride(ride)
				.amount(amount)
				.transactionType(TransactionType.DEBIT)
				.transactionMethod(transactionMethod)
				.build();
	//due to error as wallet id already exist	//walletTransactionService.creatNewWalletTransaction(walletTransaction);
		wallet.getTransactions().add(walletTransaction);
		
		return walletRepository.save(wallet);
	}

	@Override
	public Wallet findByUser(User user) {
		return walletRepository.findByUser(user)
				.orElseThrow(() -> new ResourceNotFoundException("Wallet not found for user with id: " + user.getId()));
	}

	@Override
	public void withdrawAllMyMoneyFromWallet(Long userId, Double amount) {
		// TODO Auto-generated method stub

	}

	@Override
	public Wallet findWalletById(Long walletId) {
		return walletRepository.findById(walletId)
				.orElseThrow(() -> new ResourceNotFoundException(" wallet not found with id " + walletId));
	}

	@Override
	public Wallet creatNewWallet(User user) {
		Wallet walllet = new Wallet();
		walllet.setUser(user);
		walletRepository.save(walllet);
		return null;
	}

}
