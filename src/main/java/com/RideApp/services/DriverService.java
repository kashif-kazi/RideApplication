package com.RideApp.services;

import com.RideApp.dto.DriverDto;
import com.RideApp.dto.RideDto;
import com.RideApp.dto.RiderDto;
import com.RideApp.entities.Driver;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface DriverService {

    RideDto acceptRide(Long rideRequestId);

    RideDto cancelRide(Long rideId);

    RideDto startRide(Long rideId, String otp);

    RideDto endRide(Long rideId);

    RiderDto rateRider(Long rideId, Integer rating);

    DriverDto getMyProfile();

    Driver getCurrentDriver();

	Page<RideDto> getAllMyRides(PageRequest pageRequest);

	Driver updateDriverAvailibility( Driver driver, boolean available);
	
	Driver createNewDriver(Driver driver);
}
