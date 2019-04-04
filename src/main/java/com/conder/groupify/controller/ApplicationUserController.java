package com.conder.groupify.controller;

import com.conder.groupify.domain.ApplicationUser;
import com.conder.groupify.service.ApplicationUserServiceImpl;
import com.google.common.collect.ImmutableMap;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ApplicationUserController {

  private final ApplicationUserServiceImpl applicationUserService;

  public ApplicationUserController(ApplicationUserServiceImpl applicationUserService) {
    this.applicationUserService = applicationUserService;
  }

  @PostMapping("/api/v1/users/sign-up")
  public ResponseEntity<?> signUp(@RequestBody ApplicationUser user) {
    try {
      user.setTravelRadius(200);
      String username = applicationUserService.signUp(user);
      return ResponseEntity.ok(ImmutableMap.of("user", username));
    } catch (RuntimeException e) {
      return ResponseEntity.ok(ImmutableMap.of("error", e.getMessage()));
    }
  }

  @GetMapping("/songkick/get-travel-radius")
  public ResponseEntity<Map<String, Integer>> getTravelRadius(@AuthenticationPrincipal ApplicationUser applicationUser) {
    return ResponseEntity.ok(ImmutableMap.of("currentTravelRadius", applicationUser.getTravelRadius()));
  }

  @GetMapping("/songkick/update-travel-radius")
  public ResponseEntity<Map<String, Boolean>> updateTravelRadius(@AuthenticationPrincipal ApplicationUser applicationUser, @Param("radius") Integer radius) {
    applicationUserService.updateTravelRadius(applicationUser, radius);
    return ResponseEntity.ok(ImmutableMap.of("success", true));
  }
}
