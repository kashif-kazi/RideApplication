package com.RideApp.strategies.impl;

import com.RideApp.dto.RideRequestDto;
import com.RideApp.entities.Driver;
import com.RideApp.entities.RideRequest;
import com.RideApp.repositories.DriverRepository;
import com.RideApp.strategies.DriverMatchingStrategy;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class DriverMatchingHighestRatedDriverStrategy implements DriverMatchingStrategy {
  
	private final DriverRepository driverRepository;
	@Override
    public List<Driver> findMatchingDriver(RideRequest rideRequest) {
       
		return driverRepository.findNearByTenTopRatedDriver(rideRequest.getPickupLocation());
    	
    	
    }
}
