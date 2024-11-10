package com.RideApp.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.RideApp.dto.DriverDto;
import com.RideApp.dto.RateDto;
import com.RideApp.dto.RideDto;
import com.RideApp.dto.RideStartDto;
import com.RideApp.dto.RiderDto;
import com.RideApp.services.DriverService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/drivers")
public class DriverController {

	private final DriverService driverservice;
	
	
	@PostMapping("/acceptRide/{rideRequestId}")
	public ResponseEntity<RideDto> acceptRide (@PathVariable Long rideRequestId)
	{				
		return ResponseEntity.ok(driverservice.acceptRide(rideRequestId));
		
	}
	
	
	@PostMapping("/startRide/{rideRequestId}")
	public ResponseEntity<RideDto> startRide(@PathVariable Long  rideRequestId, 
			@RequestBody RideStartDto rideStartDto){
		
		return  ResponseEntity.ok(driverservice.startRide(rideRequestId,rideStartDto.getOtp() ));
	}
	
	

	@PostMapping("/endRide/{rideId}")
	public ResponseEntity<RideDto> endRide(@PathVariable Long  rideId){
		
		return  ResponseEntity.ok(driverservice.endRide(rideId));
	}
	
	
	@PostMapping("/cancelRide/{rideId}")
	public ResponseEntity<RideDto> cancelRide(@PathVariable Long rideId){
		
		return ResponseEntity.ok(driverservice.cancelRide(rideId));
	}
	
	
	@PostMapping("/rateRider")
	public ResponseEntity<RiderDto> rateDriver(@RequestBody RateDto rateDto) {
		return ResponseEntity.ok(driverservice.rateRider(rateDto.getRideId(), rateDto.getRating()));
		
	}
	
	@GetMapping("/getMyprofile")
	public ResponseEntity<DriverDto> getMyProfile() {
		return ResponseEntity.ok(driverservice.getMyProfile());
	}
	
	@GetMapping("/getAllMyrides")
	public ResponseEntity<Page<RideDto>> getAllMyRides(@RequestParam (defaultValue = "0") Integer pageOffSet,
			@RequestParam (defaultValue = "10") Integer pageSize)
	{
		PageRequest pageRequest = PageRequest.of(pageOffSet, pageSize);
		return ResponseEntity.ok(driverservice.getAllMyRides(pageRequest));
	}
	
	 @PostMapping("/rateRider/{rideId}/{rating}")
	 public ResponseEntity<RiderDto>  rateRider(@PathVariable Long rideId, @PathVariable Integer rating) {
	return	 ResponseEntity.ok(driverservice.rateRider(rideId, rating));
	 }
	
}
