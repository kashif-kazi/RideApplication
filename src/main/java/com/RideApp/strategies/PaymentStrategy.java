package com.RideApp.strategies;

import com.RideApp.entities.Payment;

public interface PaymentStrategy {

	Double PLATFORM_COMMISSION = 0.3;

	void processPayment(Payment payment);
}
