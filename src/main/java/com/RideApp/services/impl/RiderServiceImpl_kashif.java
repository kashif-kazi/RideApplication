package com.RideApp.services.impl;
/*
 * package com.codingshuttle.project.uber.uberApp.services.impl;
 * 
 * import com.codingshuttle.project.uber.uberApp.dto.DriverDto; import
 * com.codingshuttle.project.uber.uberApp.dto.RideDto; import
 * com.codingshuttle.project.uber.uberApp.dto.RideRequestDto; import
 * com.codingshuttle.project.uber.uberApp.dto.RiderDto; import
 * com.codingshuttle.project.uber.uberApp.entities.RideRequest; import
 * com.codingshuttle.project.uber.uberApp.entities.Rider; import
 * com.codingshuttle.project.uber.uberApp.entities.User; import
 * com.codingshuttle.project.uber.uberApp.entities.enums.RideRequestStatus;
 * import
 * com.codingshuttle.project.uber.uberApp.exceptions.ResourceNotFoundException;
 * import
 * com.codingshuttle.project.uber.uberApp.repositories.RideRequestRepository;
 * import com.codingshuttle.project.uber.uberApp.repositories.RiderRepository;
 * import com.codingshuttle.project.uber.uberApp.services.RiderService; import
 * com.codingshuttle.project.uber.uberApp.strategies.DriverMatchingStrategy;
 * import com.codingshuttle.project.uber.uberApp.strategies.
 * RideFareCalculationStrategy; import
 * com.codingshuttle.project.uber.uberApp.strategies.RideStrategyManager;
 * 
 * import jakarta.transaction.Transactional; import
 * lombok.RequiredArgsConstructor;
 * 
 * import org.modelmapper.ModelMapper; import
 * org.springframework.boot.autoconfigure.info.ProjectInfoProperties.Build;
 * import org.springframework.stereotype.Service;
 * 
 * import java.util.List;
 * 
 * @Service
 * 
 * @RequiredArgsConstructor
 * 
 * @Transactional public class RiderServiceImpl_kashif implements RiderService {
 * 
 * private final ModelMapper modelMapper; private final RideRequestRepository
 * rideRequestRepository; private final RiderRepository riderRepository; private
 * final RideStrategyManager rideStrategyManager;
 * 
 * 
 * 
 * @Override public RideRequestDto requestRide(RideRequestDto rideRequestDto) {
 * // to get rider rating Rider rider = getCurrentRider();
 * 
 * 
 * RideRequest rideRequest = modelMapper.map(rideRequestDto, RideRequest.class);
 * rideRequest.setRideRequestStatus(RideRequestStatus.PENDING);
 * rideRequest.setRider(rider); // setting the rider in the Ridequest dto and
 * class where will retrive in the response
 * 
 * // to calculate fare //Double fare =
 * rideFareCalculationStrategy.calculateFare(rideRequest); Double fare =
 * rideStrategyManager.rideFareCalculationStrategy()
 * .calculateFare(rideRequest); rideRequest.setFare(fare); RideRequest
 * saveRideRequest = rideRequestRepository.save(rideRequest);
 * 
 * // to match the driver // Before ride strategy manager
 * driverMatchingStrategy.findMatchingDriver(rideRequest);
 * rideStrategyManager.driverMatchingStrategy(rider.getRating()).
 * findMatchingDriver(rideRequest);
 * 
 * return modelMapper.map(saveRideRequest, RideRequestDto.class); }
 * 
 * @Override public RideDto cancelRide(Long rideId) { return null; }
 * 
 * @Override public DriverDto rateDriver(Long rideId, Integer rating) { return
 * null; }
 * 
 * @Override public RiderDto getMyProfile() { return null; }
 * 
 * @Override public List<RideDto> getAllMyRides() { return List.of(); }
 * 
 * @Override public Rider createNewRider(User user) {
 * 
 * Rider rider = Rider.builder().user(user).rating(0.0).build(); return
 * riderRepository.save(rider); }
 * 
 * @Override public Rider getCurrentRider() { return
 * riderRepository.findById(1L).orElseThrow(()-> new ResourceNotFoundException(
 * "Rider not found with id " + 1 )); } }
 */