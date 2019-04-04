package com.conder.groupify.security.user;

import com.conder.groupify.domain.ApplicationUser;
import com.conder.groupify.repository.ApplicationUserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

  private final ApplicationUserRepository applicationUserRepository;

  public UserDetailsServiceImpl(ApplicationUserRepository applicationUserRepository) {
    this.applicationUserRepository = applicationUserRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    System.out.println("QTDDUUUDE");
    ApplicationUser applicationUser = applicationUserRepository.findByEmail(email);
    if (applicationUser == null) {
      throw new UsernameNotFoundException(email);
    }
    return new User(
        applicationUser.getEmail(), applicationUser.getPassword(), Collections.emptyList());
  }
}
