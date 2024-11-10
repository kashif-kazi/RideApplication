package com.RideApp.strategies.impl;

import org.springframework.stereotype.Service;

import com.RideApp.entities.Driver;
import com.RideApp.entities.Payment;
import com.RideApp.entities.Wallet;
import com.RideApp.enums.PaymentStatus;
import com.RideApp.enums.TransactionMethod;
import com.RideApp.repositories.PaymentRepository;
import com.RideApp.services.DriverService;
import com.RideApp.services.PaymentService;
import com.RideApp.services.WalletService;
import com.RideApp.strategies.PaymentStrategy;

import lombok.RequiredArgsConstructor;

//eg. if ride amount is 100 then 30% percent is platform charge , 
//will deduct 30rs from drivers wallet


@Service
@RequiredArgsConstructor
public class CashPaymentStrategy implements PaymentStrategy {
	
	private final WalletService walletService;
	//private final PaymentService paymentService;
	private final PaymentRepository paymentRepository;
	@Override
	public void processPayment(Payment payment) {

		Driver driver = payment.getRide().getDriver();  // to get driver
		 
		Wallet driverwallet = walletService.findByUser(driver.getUser());
		
		double platformCommision = payment.getAmount() * PLATFORM_COMMISSION;   // calculating commission
		
		
		walletService.deductMoneyFromWallet(driver.getUser(), platformCommision, null,
				payment.getRide(), TransactionMethod.RIDE);
		
		
// due to error at dependency		//paymentService.updatePayStatus(payment, PaymentStatus.CONFIRMED);  // save payment
		
		payment.setPaymentStatus(PaymentStatus.CONFIRMED);
		paymentRepository.save(payment);
		
	}

}
