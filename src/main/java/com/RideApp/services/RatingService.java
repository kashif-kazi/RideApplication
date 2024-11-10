package com.RideApp.services;

import java.util.Optional;

import com.RideApp.dto.DriverDto;
import com.RideApp.dto.RiderDto;
import com.RideApp.entities.Driver;
import com.RideApp.entities.Rating;
import com.RideApp.entities.Ride;
import com.RideApp.entities.Rider;

public interface RatingService {

	DriverDto rateDriver(Ride ride, Integer rating);
	RiderDto rateRider(Ride ride, Integer rating);
	
	void creatNewRating(Ride ride);
}
