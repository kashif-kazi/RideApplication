package com.RideApp.strategies;

import java.util.List;

import com.RideApp.entities.Driver;
import com.RideApp.entities.RideRequest;

public interface DriverMatchingStrategy {

    List<Driver> findMatchingDriver(RideRequest rideRequest);
}
