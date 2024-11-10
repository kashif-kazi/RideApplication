package com.RideApp.strategies;

import org.springframework.stereotype.Component;

import com.RideApp.enums.PaymentMethod;
import com.RideApp.enums.TransactionMethod;
import com.RideApp.enums.TransactionType;
import com.RideApp.strategies.impl.CashPaymentStrategy;
import com.RideApp.strategies.impl.WalletPaymentStrategy;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PaymentStrategyManager {

	private final WalletPaymentStrategy walletPaymentStrategy;
	private final CashPaymentStrategy cashPaymentStrategy;
	
	
	public PaymentStrategy paymentStrategy(PaymentMethod paymentMethod) {
		
		
		if (paymentMethod.equals(paymentMethod.CASH)) {
			return cashPaymentStrategy;
		}
		else if(paymentMethod.equals(paymentMethod.WALLET))
		{
			return walletPaymentStrategy;
		}
		else {
			throw new RuntimeException("Invalid Payment Method ");
		}
		
	
		
		
	}
	
}
