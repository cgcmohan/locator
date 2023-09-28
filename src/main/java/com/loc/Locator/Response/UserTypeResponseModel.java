package com.loc.Locator.Response;

import java.util.ArrayList;
import java.util.List;

import com.loc.Locator.MiscPojo.UserType;

public class UserTypeResponseModel {

	List<UserType> userTypeResponseList = new ArrayList<>();

	public List<UserType> getUserTypeResponseList() {
		return userTypeResponseList;
	}

	public void setUserTypeResponseList(List<UserType> userTypeResponseList) {
		this.userTypeResponseList = userTypeResponseList;
	}
	
	
}
