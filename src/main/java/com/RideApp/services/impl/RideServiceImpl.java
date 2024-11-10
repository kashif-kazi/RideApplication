package com.RideApp.services.impl;

import com.RideApp.dto.RideRequestDto;
import com.RideApp.entities.Driver;
import com.RideApp.entities.Ride;
import com.RideApp.entities.RideRequest;
import com.RideApp.entities.Rider;
import com.RideApp.entities.User;
import com.RideApp.enums.RideRequestStatus;
import com.RideApp.enums.RideStatus;
import com.RideApp.exceptions.ResourceNotFoundException;
import com.RideApp.repositories.RideRepository;
import com.RideApp.repositories.RideRequestRepository;
import com.RideApp.services.RideRequestService;
import com.RideApp.services.RideService;

import org.springframework.data.domain.Pageable;

import lombok.RequiredArgsConstructor;

import java.util.Random;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RideServiceImpl implements RideService {
	
	
	private final ModelMapper modelMapper;
    private final RideRequestService rideRequestService;
    private final RideRepository rideRepository;
	
    @Override
    public Ride getRideById(Long rideId) {
        return rideRepository.findById(rideId).orElseThrow(() -> new ResourceNotFoundException(" ride id not found  " + rideId));
    }

    @Override
    public void matchWithDrivers(RideRequestDto rideRequestDto) {

    }

    @Override
    public Ride createNewRide(RideRequest rideRequest, Driver driver) {
    	
  	  rideRequest.setRideRequestStatus(RideRequestStatus.CONFIRMED);  // driver accept the ride as status changed from pending to confirm
       
  	  Ride ride = modelMapper.map(rideRequest, Ride.class);
  	  
  	  ride.setDriver(driver);  
  	   
  	  ride.setOtp(generateOTP());
  	  
  	rideRequestService.update(rideRequest);
  	  
  	  return rideRepository.save(ride);
    }

    @Override
    public Ride updateRideStatus(Ride ride , RideStatus rideStatus) {  // start ride method for driver
        ride.setRideStatus(rideStatus);
    	return rideRepository.save(ride);
    			
    }

    

    
    
    // to generate otp
    public String generateOTP() {
		 Random random = new Random();
		 
		int optint =  random.nextInt(10000);   // it willl generated random number from 0 to 99999
		 
		return String.format("%04d", optint);
		 
    }

    @Override
    public Page<Ride> getAllRidesOfRider(Rider rider, PageRequest pageRequest) {
        return rideRepository.findByRider(rider, pageRequest);
    }

    @Override
    public Page<Ride> getAllRidesOfDriver(Driver driver, PageRequest pageRequest) {
        return rideRepository.findByDriver(driver, pageRequest);
    }
	
}
