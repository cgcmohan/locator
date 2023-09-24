package com.loc.Locator.BusinessLogic;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.loc.Locator.Entity.UserLocation;
import com.loc.Locator.Response.UserByLocationResponse;
import com.loc.Locator.Service.LocatorService;
import com.loc.Locator.Utility.LocatorUtil;

@Component
public class LocatorBusiness {

	private LocatorService locatorService;
	
	@Autowired
	public LocatorBusiness(LocatorService locatorService) {
		this.locatorService = locatorService;
	}

	public List<UserByLocationResponse> getNearestUsers(int n) {
		List<UserByLocationResponse> userByLocationResponse = new ArrayList<>();
		List<UserLocation> nearestUsersList = locatorService.getNearestUsers();
		nearestUsersList.sort(Comparator.comparingDouble(c->LocatorUtil.calculateDistance(c.getLatitude(), c.getLongitude(), 0, 0) ));
		List<UserLocation> collectUserLocation = nearestUsersList.stream().limit(n).collect(Collectors.toList());
		collectUserLocation.forEach(action-> {
			UserByLocationResponse userData = new UserByLocationResponse();
			userData.setUserName(action.getName());
			userData.setLatitude(action.getLatitude());
			userData.setLongitude(action.getLongitude());
			double distance = LocatorUtil.calculateDistance(action.getLatitude(), action.getLongitude(), 0 , 0);
			userData.setDistance(distance);
			userByLocationResponse.add(userData);
		});
		return userByLocationResponse;
	}

	public boolean addUserLocation(UserLocation userLocation) {
		boolean status = locatorService.addUserLocation(userLocation);
		return true;
	}
}
