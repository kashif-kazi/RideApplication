package com.RideApp.strategies.impl;

import org.springframework.stereotype.Service;

import com.RideApp.dto.RideRequestDto;
import com.RideApp.entities.RideRequest;
import com.RideApp.services.DistanceService;
import com.RideApp.strategies.RideFareCalculationStrategy;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RiderFareDefaultFareCalculationStrategy implements RideFareCalculationStrategy {
  
	
	private final DistanceService distanceService ;
	
	@Override
    public double calculateFare(RideRequest rideRequest) {
	// to calculate the distance 
		Double distance = distanceService.calculateDistance(rideRequest.getPickupLocation(),
				rideRequest.getDropOffLocation());
	// distance Multiply by 10 	
		return distance*RIDE_FARE_MULTIPLIER;
    }
}
