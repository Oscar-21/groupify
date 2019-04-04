package com.conder.groupify.service;

import com.conder.groupify.domain.ApplicationUser;
import com.conder.groupify.repository.ApplicationUserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserServiceImpl implements ApplicationUserService {

  private final ApplicationUserRepository applicationUserRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  public ApplicationUserServiceImpl(
      ApplicationUserRepository applicationUserRepository,
      BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.applicationUserRepository = applicationUserRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }

  @Override
  public String signUp(ApplicationUser user) throws RuntimeException {
    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    ApplicationUser newUser = applicationUserRepository.save(user);
    return newUser.getEmail();
  }

  @Override
  public void updateTravelRadius(ApplicationUser applicationUser, Integer travelRadius) throws RuntimeException {
    applicationUser.setTravelRadius(travelRadius);
    applicationUserRepository.save(applicationUser);
  }

}
