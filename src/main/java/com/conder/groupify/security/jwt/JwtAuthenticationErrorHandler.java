package com.conder.groupify.security.jwt;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

@Component
public class JwtAuthenticationErrorHandler implements AuthenticationEntryPoint, Serializable {

  private static final long serialVersionUID = -8970718410437077606L;

  @Override
  public void commence(
      HttpServletRequest req, HttpServletResponse res, AuthenticationException authEx)
      throws IOException {
    // This is invoked when user tries to access a secured REST resource
    // without supplying any credentials
    res.getWriter().print("{\"error\":\"true\"}");
  }
}
