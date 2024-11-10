package com.RideApp.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.RideApp.dto.DriverDto;
import com.RideApp.dto.RiderDto;
import com.RideApp.entities.Driver;
import com.RideApp.entities.Rating;
import com.RideApp.entities.Ride;
import com.RideApp.entities.Rider;
import com.RideApp.exceptions.ResourceNotFoundException;
import com.RideApp.exceptions.RuntimeConflictException;
import com.RideApp.repositories.DriverRepository;
import com.RideApp.repositories.RatingRepository;
import com.RideApp.repositories.RiderRepository;
import com.RideApp.services.RatingService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

	private final RatingRepository ratingRepository;
	private final DriverRepository driverRepository;
	private final ModelMapper modelMapper;
	private final RiderRepository riderRepository;
	
	

	@Override
	public DriverDto rateDriver(Ride ride, Integer rating) {
		   Driver driver = ride.getDriver();
	        Rating ratingObj = ratingRepository.findByRide(ride)
	                .orElseThrow(() -> new ResourceNotFoundException("Rating not found for ride with id: "+ride.getId()));

	        if(ratingObj.getDriverRating() != null)
	            throw new RuntimeConflictException("Driver has already been rated, cannot rate again");

	        ratingObj.setDriverRating(rating);

	        ratingRepository.save(ratingObj);

	        Double newRating = ratingRepository.findByDriver(driver)
	                .stream()
	                .mapToDouble(Rating::getDriverRating)
	                .average().orElse(0.0);
	        driver.setRating(newRating);

	        Driver savedDriver = driverRepository.save(driver);
	        return modelMapper.map(savedDriver, DriverDto.class);
	}



	@Override
	public RiderDto rateRider(Ride ride, Integer rating) {
		Rider rider = ride.getRider();
        Rating ratingObj = ratingRepository.findByRide(ride)
                .orElseThrow(() -> new ResourceNotFoundException("Rating not found for ride with id: "+ride.getId()));
        if(ratingObj.getRiderRating() != null)
            throw new RuntimeConflictException("Rider has already been rated, cannot rate again");

        ratingObj.setRiderRating(rating);

        ratingRepository.save(ratingObj);

        Double newRating = ratingRepository.findByRider(rider)
                .stream()
                .mapToDouble(Rating::getRiderRating)
                .average().orElse(0.0);
        rider.setRating(newRating);// calculating the average rating of rider rating

        Rider savedRider = riderRepository.save(rider);
        return modelMapper.map(savedRider, RiderDto.class);
	}



	@Override
	public void creatNewRating(Ride ride) {
		Rating rating = Rating.builder()
				.rider(ride.getRider())
				.driver(ride.getDriver())
				.ride(ride).build();
		ratingRepository.save(rating);
		
	}

	
	

}
