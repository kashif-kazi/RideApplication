package com.RideApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RideApp.entities.Rider;

@Repository
public interface RiderRepository extends JpaRepository<Rider, Long> {
}
