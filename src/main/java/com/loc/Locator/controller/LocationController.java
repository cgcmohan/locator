package com.loc.Locator.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.loc.Locator.BusinessLogic.LocatorBusiness;
import com.loc.Locator.Entity.UserLocation;
import com.loc.Locator.Entity.UserTypeEntity;
import com.loc.Locator.Response.UserByLocationResponse;
import com.loc.Locator.Response.UserTypeResponseModel;

@RestController
@RequestMapping(value = "location")
public class LocationController {

	private LocatorBusiness locatorBusiness;
	
	@Autowired
	public LocationController(LocatorBusiness locatorBusiness) {
		this.locatorBusiness = locatorBusiness;
	}
	
	@GetMapping("/check")
	public String check() {
		return "Yo! I am here now...";
	}
	
	@GetMapping("/verify")
	public String verify() {
		return "User verified! Next Please...";
	}
	
	@PostMapping(value = "/nearestuser")
	public List<UserByLocationResponse> getNearestUsers(@RequestParam int n) {
		List<UserByLocationResponse> allNearestUserList = locatorBusiness.getNearestUsers(n);
		return allNearestUserList;
	}
	
	@PostMapping(value = "/add") 
	public boolean addUserLocation(@RequestBody UserLocation userLocation) {
		boolean status = locatorBusiness.addUserLocation(userLocation);
		return true;
	}
	@GetMapping(value = "/rest-check") 
	public String getRestData() {
		 return locatorBusiness.getRestData();
	}
	
	@PostMapping(value = "/get-user-by-diet") 
	public UserTypeResponseModel getUserByDiet(@RequestBody Map<String, String> diet) {
		
		UserTypeResponseModel response = locatorBusiness.getUserByDiet(diet);
		return response;
	}
	
	@PostMapping(value = "/add-user-by-diet") 
	public UserTypeEntity addUserByDiet(@RequestBody UserTypeEntity userType) {
		
		UserTypeEntity response = locatorBusiness.addUserByDiet(userType);
		return response;
	}
}
