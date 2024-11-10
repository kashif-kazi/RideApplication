package com.RideApp.services.impl;

import jakarta.transaction.Transactional;

import com.RideApp.dto.DriverDto;
import com.RideApp.dto.SignupDto;
import com.RideApp.dto.UserDto;
import com.RideApp.entities.Driver;
import com.RideApp.entities.Ride;
import com.RideApp.entities.Rider;
import com.RideApp.entities.User;
import com.RideApp.enums.Role;
import com.RideApp.exceptions.ResourceNotFoundException;
import com.RideApp.exceptions.RuntimeConflictException;
import com.RideApp.repositories.UserRepository;
import com.RideApp.services.AuthService;
import com.RideApp.services.DriverService;
import com.RideApp.services.RideService;
import com.RideApp.services.RiderService;
import com.RideApp.services.WalletService;

import lombok.RequiredArgsConstructor;

import java.util.Set;

import javax.management.RuntimeErrorException;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final ModelMapper modelMapper;
	private final UserRepository userRepository;
	private final RiderService riderService;
	private final WalletService walletService;
	private final DriverService driverService;
	

	// TOOD it required Spring security
	@Override
	public String login(String email, String password) {
		return "";
	}

	
	@Override
	@Transactional
	public UserDto signup(SignupDto signupDto) {
		
		User user = userRepository.findByEmail(signupDto.getEmail()).orElse(null);
        if(user != null)
                throw new RuntimeConflictException("Cannot signup, User already exists with email "+signupDto.getEmail());


		User mappedUser = modelMapper.map(signupDto, User.class);
		mappedUser.setRoles(Set.of(Role.RIDER)); // set the role as rider then convert it to savedUser in database
		User savedUser = userRepository.save(mappedUser);
		// created new Rider as every user is rider	
		Rider rider = riderService.createNewRider(savedUser);
		walletService.creatNewWallet(savedUser); // whener user sign in wallet will be created
		// TODO Add wallet related service here
		return modelMapper.map(savedUser, UserDto.class);
	}

	
	
	
	
	@Override
	public DriverDto onboardNewDriver(Long userId, String vehileId) {
		
		User user = userRepository.findById(userId).orElseThrow
				(() -> new ResourceNotFoundException(" Driver not found with id " + userId));
		
		if(user.getRoles().contains(Role.DRIVER)) {
			throw new RuntimeException(" User with id " + userId + " is already present");
		}
		 Driver currerntDriver = Driver.builder()
				 .user(user)
				 .rating(0.0)
				 .available(true)
				 .vehicleId(vehileId)
				 .build();
		 user.getRoles().add(Role.DRIVER) ;
		 userRepository.save(user);
		 Driver savedDriver = driverService.createNewDriver(currerntDriver);
		
		return modelMapper.map(savedDriver, DriverDto.class);
	}
}
