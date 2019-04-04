package com.conder.groupify.security;

import com.auth0.jwt.JWT;
import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.conder.groupify.security.SecurityConstants.EXPIRATION_TIME;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

  @Value("${app.token.secret}")
  private String SECRET;

  @Override
  public void onAuthenticationSuccess(
      HttpServletRequest req, HttpServletResponse res, Authentication auth)
      throws IOException, ServletException {
    String email = ((User) auth.getPrincipal()).getUsername();
    String token =
        JWT.create()
            .withSubject(email)
            .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .sign(HMAC512(SECRET.getBytes()));

    String json = new Gson().toJson(ImmutableMap.of("token", token));
    res.setContentType("application/json");
    res.setCharacterEncoding("UTF-8");
    res.getWriter().print(json);
    res.getWriter().flush();
  }
}
