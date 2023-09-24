package com.loc.Locator.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loc.Locator.Entity.UserLocation;
import com.loc.Locator.Repository.UserLocationRepository;

@Service
public class LocatorService {

	private UserLocationRepository userLocationRepo;
	
	@Autowired
	public LocatorService(UserLocationRepository userLocationRepo) {
		this.userLocationRepo = userLocationRepo;
	}
	public List<UserLocation> getNearestUsers() {
		List<UserLocation> userListByLocation = userLocationRepo.findAll();
		return userListByLocation;
	}
	public boolean addUserLocation(UserLocation userLocation) {
		UserLocation status = userLocationRepo.save(userLocation);
		return true;
	}

	
}
