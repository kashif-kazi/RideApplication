package com.RideApp.entities;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(indexes = {
		@Index(name = "idex_rating_rider", columnList = "rider_id"),
		@Index(name = "idex_rating_driver", columnList = "driver_id")
})
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Rating {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long id;
	
	@OneToOne                  // one ride had belongs to only one rating
	private Ride ride;
	
	@ManyToOne
	private Rider rider;
	
	@ManyToOne					// one driver can have many rating
	private Driver driver;
	
	
	private Integer driverRating;
	private Integer riderRating;
}
