package com.RideApp.services;

import com.RideApp.entities.Payment;
import com.RideApp.entities.Ride;
import com.RideApp.enums.PaymentStatus;

public interface PaymentService {

    void processPayment(Ride ride);

    Payment createNewPayment(Ride ride);

    void updatePaymentStatus(Payment payment, PaymentStatus status);
}
