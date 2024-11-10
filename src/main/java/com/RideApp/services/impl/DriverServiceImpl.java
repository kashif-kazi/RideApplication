package com.RideApp.services.impl;

import com.RideApp.dto.DriverDto;
import com.RideApp.dto.RideDto;
import com.RideApp.dto.RiderDto;
import com.RideApp.entities.Driver;
import com.RideApp.entities.Ride;
import com.RideApp.entities.RideRequest;
import com.RideApp.entities.Rider;
import com.RideApp.enums.RideRequestStatus;
import com.RideApp.enums.RideStatus;
import com.RideApp.exceptions.ResourceNotFoundException;
import com.RideApp.repositories.DriverRepository;
import com.RideApp.services.DriverService;
import com.RideApp.services.PaymentService;
import com.RideApp.services.RatingService;
import com.RideApp.services.RideRequestService;
import com.RideApp.services.RideService;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

	private final RideRequestService rideRequestService;
	private final DriverRepository driverRepository;
	private final RideService rideService;
	private final ModelMapper modelMapper;
	private final PaymentService paymentService;
	private final RatingService ratingService;

	@Override
	@Transactional
	public RideDto acceptRide(Long rideRequestId) {
		RideRequest rideRequest = rideRequestService.findRideRequestById(rideRequestId);

		if (!rideRequest.getRideRequestStatus().equals(RideRequestStatus.PENDING)) {
			throw new RuntimeException(
					"RideRequest cannot be accepted, status is " + rideRequest.getRideRequestStatus());
		} // driver only accept the ride if status is equal to pending

		Driver currentDriver = getCurrentDriver();
		if (!currentDriver.getAvailable()) {
			throw new RuntimeException("Driver cannot accept ride due to unavailability");
		}

		currentDriver.setAvailable(false); // after accepting ride then current dirver makes unavailable
		Driver savedDriver = driverRepository.save(currentDriver);

		Ride ride = rideService.createNewRide(rideRequest, savedDriver);
		return modelMapper.map(ride, RideDto.class);
	}

	@Override
	public RideDto cancelRide(Long rideId) {
		Ride ride = rideService.getRideById(rideId); // gettting ride id of currernt rider

		Driver driver = getCurrentDriver();

		if (!driver.equals(ride.getDriver())) {
			throw new RuntimeException(" driver cant accept the ride as its earlier not accepted");
		}

		if (!ride.getRideStatus().equals(RideStatus.CONFIRMED)) // ride can only the status is confirmed
		{
			new RuntimeException(" Ride can not be canceled invalid status " + ride.getRideStatus());
		}

		rideService.updateRideStatus(ride, RideStatus.CANCELLED); // update the ride
		// driver.setAvailable(true);// once ride has been cancelled , driver need to be
		// available once again
//    	driverRepository.save(driver); // makes driver available once again in database

		updateDriverAvailibility(driver, true);

		return modelMapper.map(ride, RideDto.class);

	}
	
	
	 @Override
	    @Transactional
	    public RideDto endRide(Long rideId) {
	        Ride ride = rideService.getRideById(rideId);
	        Driver driver = getCurrentDriver();

	        if(!driver.equals(ride.getDriver())) {
	            throw new RuntimeException("Driver cannot start a ride as he has not accepted it earlier");
	        }

	        if(!ride.getRideStatus().equals(RideStatus.ONGOING)) {
	            throw new RuntimeException("Ride status is not ONGOING hence cannot be ended, status: "+ride.getRideStatus());
	        }

	        ride.setEndedAt(LocalDateTime.now());
	        Ride savedRide = rideService.updateRideStatus(ride, RideStatus.ENDED);
	        updateDriverAvailibility(driver, true);

	        paymentService.processPayment(ride);

	        return modelMapper.map(savedRide, RideDto.class);
	    }

//	@Override
//	public RideDto endRide(Long rideId) {
//		Ride ride = rideService.getRideById(rideId);
//
//		Driver currentDriver = getCurrentDriver();
//
//		if (!currentDriver.equals(ride.getDriver())) {
//			throw new RuntimeException(" driver missmatch");
//		}
//		if (!ride.getRideStatus().equals(RideStatus.ONGOING)) {
//			throw new RuntimeException(
//					"ride status is not ongoing can not end this ride with status" + ride.getRideStatus());
//		}
//
//		ride.setEndedAt(LocalDateTime.now()); // end time for ride
//		Ride savedRide = rideService.updateRideStatus(ride, RideStatus.ENDED);
//		updateDriverAvailibility(currentDriver, true);
//
//		paymentService.processPayment(ride);
//		return modelMapper.map(savedRide, RideDto.class);
//	}

	@Override
	public RiderDto rateRider(Long rideId, Integer rating) {

		Ride ride = rideService.getRideById(rideId);
		 Driver currenTDriver = getCurrentDriver();
		 
		 if(!currenTDriver.equals(ride.getDriver())) {
			 throw new RuntimeException(" Driver is not the owner of this ride");
		 }
		 
		  if(!ride.getRideStatus().equals(RideStatus.ENDED)) {
	            throw new RuntimeException("Ride status is not Ended hence cannot start rating, status: "+ride.getRideStatus());
	        }

		 ratingService.rateRider(ride, rating);
		 return null;
	}

	@Override
	public DriverDto getMyProfile() {
		Driver currentDriver = getCurrentDriver();
		return modelMapper.map(currentDriver, DriverDto.class);

	}

	@Override
	public Page<RideDto> getAllMyRides(PageRequest pageRequest) {
		Driver currentDriver = getCurrentDriver();
		return rideService.getAllRidesOfDriver(currentDriver, pageRequest)
				.map(ride -> modelMapper.map(ride, RideDto.class));
	}

	@Override
	public Driver getCurrentDriver() {
		return driverRepository.findById(2L)
				.orElseThrow(() -> new ResourceNotFoundException("Driver not found with " + "id " + 2)); // hard coded
																											// driver
	}

	@Override
	public RideDto startRide(Long rideId, String otp) {
		Ride ride = rideService.getRideById(rideId); // finding the ride with requested ride id

		Driver driver = getCurrentDriver();

		if (!driver.equals(ride.getDriver())) { // checking the driver own the ride(driver matching) or driver is
												// correct
			throw new RuntimeException("Driver can not start a ride as he has not accepted ride earlier");
		}

		if (!ride.getRideStatus().equals(RideStatus.CONFIRMED)) // if ride is not confirmed
		{
			throw new RuntimeException(" Ride status is not confirmed cant start ride status =" + ride.getRideStatus());
		}

		if (!otp.equals(ride.getOtp())) { // validating otp
			throw new RuntimeException(" invalid otp " + otp);
		}

		ride.setStartedAt(LocalDateTime.now());
		Ride savedride = rideService.updateRideStatus(ride, RideStatus.ONGOING);
		
		 paymentService.createNewPayment(savedride);   // as ride is started create payment
		 ratingService.creatNewRating(savedride);        // rating the 
		 
		 
		return modelMapper.map(savedride, RideDto.class);

	}

	@Override
	public Driver updateDriverAvailibility(Driver driver, boolean available) {
		/*
		 * Driver driver = driverRepository.findById(driverId). orElseThrow(() -> new
		 * ResourceNotFoundException( " driver not found with id "+driverId));
		 */

		driver.setAvailable(available);
		return driverRepository.save(driver);

	}
	
	
	public Driver createNewDriver(Driver driver) {
		return driverRepository.save(driver);
	}

}
