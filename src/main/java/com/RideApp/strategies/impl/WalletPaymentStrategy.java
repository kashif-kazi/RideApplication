package com.RideApp.strategies.impl;

import org.springframework.stereotype.Service;

import com.RideApp.entities.Driver;
import com.RideApp.entities.Payment;
import com.RideApp.entities.Rider;
import com.RideApp.enums.PaymentStatus;
import com.RideApp.enums.TransactionMethod;
import com.RideApp.repositories.PaymentRepository;
import com.RideApp.repositories.WalletRepository;
import com.RideApp.services.PaymentService;
import com.RideApp.services.WalletService;
import com.RideApp.strategies.PaymentStrategy;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;





//Rider had 232, Driver had 500
//Ride cost is 100, commission = 30
//Rider -> 232-100 = 132
//Driver -> 500 + (100 - 30) = 570

@Service
@RequiredArgsConstructor
public class WalletPaymentStrategy implements PaymentStrategy {

  private final WalletService walletService;
  private final PaymentRepository paymentRepository;

  @Override
  @Transactional
  public void processPayment(Payment payment) {
      Driver driver = payment.getRide().getDriver();
      Rider rider = payment.getRide().getRider();

      walletService.deductMoneyFromWallet(rider.getUser(),
              payment.getAmount(), null, payment.getRide(), TransactionMethod.RIDE);

      double driversCut = payment.getAmount() * (1 - PLATFORM_COMMISSION);

      walletService.addMoneyToWallet(driver.getUser(),
              driversCut, null, payment.getRide(), TransactionMethod.RIDE);

      payment.setPaymentStatus(PaymentStatus.CONFIRMED);
      paymentRepository.save(payment);
  }
}















/*
 * // rider had 300 , driver 500 // ride fare amount is 100 - platform
 * commission 0.3% =30 // rider > 300-100=200 // driver > 500 + (100-30)
 * 
 * 
 * @Service
 * 
 * @RequiredArgsConstructor public class WalletPaymentStrategy implements
 * PaymentStrategy { private final WalletService walletService; private final
 * PaymentRepository paymentRepository; //private final PaymentService
 * paymentService;
 * 
 * 
 * @Override public void processPayment(Payment payment) { Driver driver =
 * payment.getRide().getDriver(); //driver Rider rider =
 * payment.getRide().getRider(); // rider
 * 
 * walletService.deductMoneyFromWallet(rider.getUser(), payment.getAmount(),
 * null, payment.getRide(), TransactionMethod.RIDE);
 * 
 * double driverCut = payment.getAmount() * (1- PLATFORM_COMMISSION ); //
 * deducting commision
 * 
 * 
 * walletService.addMoneyToWallet(driver.getUser(), driverCut, null,
 * payment.getRide(), TransactionMethod.RIDE);
 * 
 * // paymentService.updatePayStatus(payment, PaymentStatus.CONFIRMED); //
 * commented due depency error
 * payment.setPaymentStatus(PaymentStatus.CONFIRMED);
 * paymentRepository.save(payment);
 * 
 * }
 * 
 * }
 */
