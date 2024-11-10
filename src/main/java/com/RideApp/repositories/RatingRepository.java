package com.RideApp.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.RideApp.entities.Driver;
import com.RideApp.entities.Rating;
import com.RideApp.entities.Ride;
import com.RideApp.entities.Rider;

public interface RatingRepository extends JpaRepository<Rating, Long> {

	List<Rating> findByRider(Rider rider);
	
	List<Rating> findByDriver(Driver driver);
	
	Optional<Rating> findByRide(Ride ride);
}
