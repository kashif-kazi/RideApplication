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
import com.RideApp.dto.RideRequestDto;
import com.RideApp.dto.RiderDto;
import com.RideApp.services.RiderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/rider")
@RequiredArgsConstructor
public class RiderController {

	private final RiderService riderService;
	
	@PostMapping("/requestride")
	public ResponseEntity<RideRequestDto> riderequest(@RequestBody RideRequestDto rideRequestDto) {
		 return ResponseEntity.ok(riderService.requestRide(rideRequestDto));
	}
	
	
	@PostMapping("/cancelRide/{rideId}")
	public ResponseEntity<RideDto> cancelRide(@PathVariable Long rideId){
		
		return ResponseEntity.ok(riderService.cancelRide(rideId));
	}
	
	@PostMapping("/rateDriver")
	public ResponseEntity<DriverDto> rateDriver(@RequestBody RateDto rateDto) {
		return ResponseEntity.ok(riderService.rateDriver(rateDto.getRideId(), rateDto.getRating()));
		
	}
	
	@GetMapping("/getMyprofile")
	public ResponseEntity<RiderDto> getMyProfile() {
		return ResponseEntity.ok(riderService.getMyProfile());
	}
	
	@GetMapping("/getAllMyrides")
	public ResponseEntity<Page<RideDto>> getAllMyRides(@RequestParam (defaultValue = "0") Integer pageOffSet,
			@RequestParam (defaultValue = "10") Integer pageSize)
	{
		PageRequest pageRequest = PageRequest.of(pageOffSet, pageSize);
		return ResponseEntity.ok(riderService.getAllMyRides(pageRequest));
	}
	
	
	@PostMapping("/rateDriver/{rideId}/{rating}/")
	public ResponseEntity<DriverDto> rateDriver
	(@PathVariable Long rideId, @PathVariable Integer rating )
	{
		return ResponseEntity.ok(riderService.rateDriver(rideId, rating));
	}
	
	
}
