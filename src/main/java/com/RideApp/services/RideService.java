package com.RideApp.services;

import com.RideApp.dto.RideDto;
import com.RideApp.dto.RideRequestDto;
import com.RideApp.entities.Driver;
import com.RideApp.entities.Ride;
import com.RideApp.entities.RideRequest;
import com.RideApp.entities.Rider;
import com.RideApp.entities.User;
import com.RideApp.enums.RideStatus;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface RideService {

    Ride getRideById(Long rideId);

    void matchWithDrivers(RideRequestDto rideRequestDto);

   //Ride createNewRide(RideRequestDto rideRequestDto, Driver driver);
    Ride createNewRide(RideRequest rideRequest, Driver driver);

    Ride updateRideStatus(Ride ride, RideStatus rideStatus);

    Page<Ride> getAllRidesOfRider(Rider rider, PageRequest pageRequest);

    Page<Ride> getAllRidesOfDriver(Driver driver, PageRequest pageRequest);

	
}
