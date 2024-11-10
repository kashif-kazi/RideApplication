package com.RideApp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RideApp.entities.User;
import com.RideApp.entities.Wallet;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long>{
	Optional<Wallet> findByUser(User user);
}
