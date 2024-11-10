package com.RideApp.services.impl;

import org.springframework.stereotype.Service;

import com.RideApp.entities.Payment;
import com.RideApp.entities.Ride;
import com.RideApp.enums.PaymentStatus;
import com.RideApp.exceptions.ResourceNotFoundException;
import com.RideApp.repositories.PaymentRepository;
import com.RideApp.services.PaymentService;
import com.RideApp.strategies.PaymentStrategyManager;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentStrategyManager paymentStrategyManager;

    @Override
    public void processPayment(Ride ride) {
        Payment payment = paymentRepository.findByRide(ride)
                        .orElseThrow(() -> new ResourceNotFoundException("Payment not found for ride with id: "+ride.getId()));
        paymentStrategyManager.paymentStrategy(payment.getPaymentMethod()).processPayment(payment);
    }

    @Override
    public Payment createNewPayment(Ride ride) {
        Payment payment = Payment.builder()
                .ride(ride)
                .paymentMethod(ride.getPaymentMethod())
                
                .amount(ride.getFare())
                .paymentStatus(PaymentStatus.PENDING)
                .build();
        return paymentRepository.save(payment);
    }

    @Override
    public void updatePaymentStatus(Payment payment, PaymentStatus status) {
        payment.setPaymentStatus(status);
        paymentRepository.save(payment);
    }
}











//@Service
//@RequiredArgsConstructor
//public class PaymentServiceImpl  implements PaymentService{
//
//	
//	private final PaymentRepository paymentRepository;
//	private final PaymentStrategyManager paymentStrategyManager ;
//	
//	/*
//	 * @Override public void processPayment(Ride ride) { Payment payment =
//	 * paymentRepository.findByRide(ride) .orElseThrow(() -> new
//	 * ResourceNotFoundException(" Payment not found with ride " + ride.getId()));
//	 * 
//	 * paymentStrategyManager.paymentStrategy(payment.getPaymentMethod())
//	 * .processPayment(payment); }
//	 */
//
//	
//	 @Override
//	    public void processPayment(Ride ride) {
//	        Payment payment = paymentRepository.findByRide(ride)
//	                        .orElseThrow(() -> new ResourceNotFoundException("Payment not found for ride with id: "+ride.getId()));
//	        paymentStrategyManager.paymentStrategy(payment.getPaymentMethod()).processPayment(payment);
//	    }
//	
//	@Override
//    public Payment createNewPayment(Ride ride) {
//        Payment payment = Payment.builder()
//                .ride(ride)
//                .paymentMethod(ride.getPaymentMethod())
//                .amount(ride.getFare())
//                .paymentStatus(PaymentStatus.PENDING)
//                .build();
//        return paymentRepository.save(payment);
//    }
//
//	@Override
//	public void updatePayStatus(Payment payment, PaymentStatus status) {
//		payment.setPaymentStatus(status);
//		paymentRepository.save(payment);
//		
//	}
//
//
//}
