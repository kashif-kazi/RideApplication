package com.RideApp.strategies;

import com.RideApp.entities.RideRequest;

public interface RideFareCalculationStrategy {

	
	double RIDE_FARE_MULTIPLIER=10;
	
    double calculateFare(RideRequest rideRequest);

}
