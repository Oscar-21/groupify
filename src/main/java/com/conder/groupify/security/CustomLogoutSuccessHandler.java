package com.conder.groupify.security;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/** CustomLogoutSuccessHandler */
@Component
public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler
    implements LogoutSuccessHandler {

  @Override
  public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse res, Authentication auth)
      throws IOException, ServletException {
    String json = new Gson().toJson(ImmutableMap.of("success", true));
    res.setContentType("application/json");
    res.setCharacterEncoding("UTF-8");
    res.getWriter().print(json);
  }
}
