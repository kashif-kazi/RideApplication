package com.RideApp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RideApp.entities.Payment;
import com.RideApp.entities.Ride;
import com.RideApp.entities.Wallet;
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

	Optional<Payment> findByRide(Ride ride);

}
