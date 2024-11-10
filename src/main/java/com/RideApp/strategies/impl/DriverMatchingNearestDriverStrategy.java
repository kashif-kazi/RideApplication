package com.RideApp.strategies.impl;

import com.RideApp.dto.RideRequestDto;
import com.RideApp.entities.Driver;
import com.RideApp.entities.RideRequest;
import com.RideApp.repositories.DriverRepository;
import com.RideApp.strategies.DriverMatchingStrategy;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class DriverMatchingNearestDriverStrategy implements DriverMatchingStrategy {

	private final DriverRepository driverRepository;
	
	
    @Override
    public List<Driver> findMatchingDriver(RideRequest rideRequest) {
    	//   TODO need to write custom query usinf JPQL
    	driverRepository.findTenNearestDrivers(rideRequest.getPickupLocation());
		
		
		return List.of();
    }
}
