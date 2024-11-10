package com.RideApp.services;

import com.RideApp.dto.DriverDto;
import com.RideApp.dto.RideDto;
import com.RideApp.dto.RideRequestDto;
import com.RideApp.dto.RiderDto;
import com.RideApp.entities.Rider;
import com.RideApp.entities.User;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface RiderService {

    RideRequestDto requestRide(RideRequestDto rideRequestDto);  

    RideDto cancelRide(Long rideId);

    DriverDto rateDriver(Long rideId, Integer rating);

    RiderDto getMyProfile();
    
    Rider createNewRider(User user);
    
    
    Rider getCurrentRider();

	Page<RideDto> getAllMyRides(PageRequest pageRequest);
}
