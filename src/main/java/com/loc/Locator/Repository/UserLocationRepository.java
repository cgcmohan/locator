package com.loc.Locator.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loc.Locator.Entity.UserLocation;

import jakarta.persistence.Query;

@Repository
public interface UserLocationRepository extends JpaRepository<UserLocation, Long>{
//
//	@Query("Select * from User_Location")
//	public List<UserLocation> getNearestUsers();

}
