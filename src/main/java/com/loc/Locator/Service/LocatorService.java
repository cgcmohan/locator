package com.loc.Locator.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.loc.Locator.Entity.UserLocation;
import com.loc.Locator.Entity.UserTypeEntity;
import com.loc.Locator.Repository.UserLocationRepository;
import com.loc.Locator.Repository.UserTypeEntityRepository;
import com.loc.Locator.Response.UserTypeResponseModel;

@Service
public class LocatorService {

	private UserLocationRepository userLocationRepo;
	
	private UserTypeEntityRepository userTypeRepo;
	RestTemplate restTemplate = new RestTemplate();
	@Autowired
	public LocatorService(UserLocationRepository userLocationRepo , UserTypeEntityRepository userTypeRepo) {
		this.userLocationRepo = userLocationRepo;
		this.userTypeRepo = userTypeRepo;
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
	public UserTypeEntity addUserByDiet(UserTypeEntity userType) {
		UserTypeEntity response = userTypeRepo.saveAndFlush(userType);
		return response;
	}
	public List<UserTypeEntity> getUserByDiet(String dietType) {
		List<UserTypeEntity> userByDietList = userTypeRepo.findByDietType(dietType);
		return userByDietList;
	}

	
}
