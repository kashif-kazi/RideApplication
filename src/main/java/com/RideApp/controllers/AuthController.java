package com.RideApp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RideApp.dto.DriverDto;
import com.RideApp.dto.OnBoardDriverDto;
import com.RideApp.dto.SignupDto;
import com.RideApp.dto.UserDto;
import com.RideApp.services.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;
	
	@PostMapping("/signup")
   ResponseEntity<UserDto>	signUp(@RequestBody SignupDto signupDto) {
		return new ResponseEntity<>( authService.signup(signupDto), HttpStatus.CREATED);
	}
	@PostMapping("/onBoardNewDriver/{userID}")
	ResponseEntity<DriverDto> onboardNewDriver(@PathVariable Long userId,
			@RequestBody OnBoardDriverDto onBoardDriverDto){
		return new ResponseEntity<>(authService.onboardNewDriver(userId,onBoardDriverDto.getVehicleId() ), HttpStatus.CREATED);
	}
	
	
	
}
