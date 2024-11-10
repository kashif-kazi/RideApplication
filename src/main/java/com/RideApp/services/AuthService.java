package com.RideApp.services;

import com.RideApp.dto.DriverDto;
import com.RideApp.dto.SignupDto;
import com.RideApp.dto.UserDto;

public interface AuthService {

    String login(String email, String password);

    UserDto signup(SignupDto signupDto);

    DriverDto onboardNewDriver(Long userId, String vehicleId);
}
