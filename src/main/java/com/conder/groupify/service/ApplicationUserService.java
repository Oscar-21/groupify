package com.conder.groupify.service;

import com.conder.groupify.domain.ApplicationUser;

public interface ApplicationUserService {
  String signUp(ApplicationUser user) throws RuntimeException;
  void updateTravelRadius(ApplicationUser applicationUser, Integer travelRadius) throws RuntimeException;
}
