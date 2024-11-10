package com.RideApp.services.impl;
/*
 * package com.codingshuttle.project.uber.uberApp.services.impl;
 * 
 * import com.codingshuttle.project.uber.uberApp.dto.DriverDto; import
 * com.codingshuttle.project.uber.uberApp.dto.RideDto; import
 * com.codingshuttle.project.uber.uberApp.dto.RiderDto; import
 * com.codingshuttle.project.uber.uberApp.entities.Driver; import
 * com.codingshuttle.project.uber.uberApp.entities.Ride; import
 * com.codingshuttle.project.uber.uberApp.entities.RideRequest; import
 * com.codingshuttle.project.uber.uberApp.entities.Rider; import
 * com.codingshuttle.project.uber.uberApp.entities.enums.RideRequestStatus;
 * import
 * com.codingshuttle.project.uber.uberApp.exceptions.ResourceNotFoundException;
 * import com.codingshuttle.project.uber.uberApp.repositories.DriverRepository;
 * import com.codingshuttle.project.uber.uberApp.services.DriverService; import
 * com.codingshuttle.project.uber.uberApp.services.RideRequestService; import
 * com.codingshuttle.project.uber.uberApp.services.RideService; import
 * com.codingshuttle.project.uber.uberApp.services.RiderService;
 * 
 * import lombok.RequiredArgsConstructor;
 * 
 * import org.modelmapper.ModelMapper; import
 * org.springframework.stereotype.Service;
 * 
 * import java.util.List;
 * 
 * @Service
 * 
 * @RequiredArgsConstructor public class DriverServiceImpl_Kashif implements
 * DriverService {
 * 
 * private final RideRequestService rideRequestService;
 * 
 * private final DriverRepository driverRepository ;
 * 
 * private final RideService rideService; private final ModelMapper modelmapper;
 * 
 * @Override public RideDto acceptRide(Long rideRequestId) {
 * 
 * 
 * if(! rideRequest.getRideRequestStatus().equals(RideRequestStatus.PENDING)){
 * throw new RuntimeException(" can not accepted , status is " +
 * rideRequest.getRideRequestStatus()) ; }// driver only accpt the ride if
 * status is pending
 * 
 * RideRequest rideRequest =
 * rideRequestService.findRideRequestById(rideRequestId);
 * 
 * if(!rideRequest.getRideRequestStatus().equals(RideRequestStatus.PENDING)) {
 * throw new RuntimeException("RideRequest cannot be accepted, status is "+
 * rideRequest.getRideRequestStatus()); }
 * 
 * 
 * 
 * Driver CurretnDriver =getCurrentDriver(); if(!CurretnDriver.getAvailable()) {
 * throw new RuntimeException(" driver unavailable"); }
 * 
 * 
 * Ride ride= rideService.createNewRide(rideRequest, CurretnDriver);
 * 
 * return modelmapper.map(ride, RideDto.class); }
 * 
 * @Override public RideDto cancelRide(Long rideId) { return null; }
 * 
 * @Override public RideDto startRide(Long rideId) { return null; }
 * 
 * @Override public RideDto endRide(Long rideId) { return null; }
 * 
 * @Override public RiderDto rateRider(Long rideId, Integer rating) { return
 * null; }
 * 
 * @Override public DriverDto getMyProfile() { return null; }
 * 
 * @Override public List<RideDto> getAllMyRides() { return List.of(); }
 * 
 * 
 * @Override public Driver getCurrentDriver() { // TODO get driver from the
 * spring sequerity
 * 
 * return driverRepository.findById(2l).orElseThrow(() -> new
 * RuntimeException(" driver not found with id 2l"));// hard coded driver }
 * 
 * 
 * 
 * @Override public Driver getCurrentDriver() { return
 * driverRepository.findById(2L).orElseThrow(() -> new
 * ResourceNotFoundException("Driver not found with " + "id "+2)); }
 * 
 * }
 */