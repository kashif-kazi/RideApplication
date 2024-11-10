package com.RideApp.repositories;

import java.util.List;

import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.RideApp.entities.Driver;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

	// finding the driver within 10km range og pickup location using custom Query
	
	@Query(value = "SELECT d.*, ST_Distance(d.current_location, :pickupLocation) AS distance " +
            "FROM drivers d " +
            "WHERE d.available = true AND ST_DWithin(d.current_location, :pickupLocation, 10000) " +
            "ORDER BY distance " +
            "LIMIT 10", nativeQuery = true)   // native query means it is sequel query
	List<Driver> findTenNearestDrivers(Point pickupLocation);
	
	
	
	//finding the top rated driver near the picuploacation within 15km distance
	 @Query(value = "SELECT d.* " +
	            "FROM driver d " +
	            "WHERE d.available = true AND ST_DWithin(d.current_location, :pickupLocation, 15000) " +
	            "ORDER BY d.rating DESC " +
	            "LIMIT 10", nativeQuery = true)
	List<Driver> findNearByTenTopRatedDriver(Point pickupLocation);
	
}
