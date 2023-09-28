package com.loc.Locator.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.loc.Locator.Entity.UserLocation;
import com.loc.Locator.Repository.UserLocationRepository;

@Service
public class LocatorService {

	private UserLocationRepository userLocationRepo;
	
	RestTemplate restTemplate = new RestTemplate();
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
	
	public String getRestData() {
		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/check", String.class);
		Map<String, Integer> req = new HashMap<>();
		req.put("qty", 10);
		ResponseEntity<String> qtyResponse  = restTemplate.postForEntity("http://localhost:8080/addbeer", req, String.class);
		return qtyResponse.getBody();
	}

	
}
