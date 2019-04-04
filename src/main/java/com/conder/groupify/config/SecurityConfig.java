package com.conder.groupify.config;

import com.conder.groupify.repository.ApplicationUserRepository;
import com.conder.groupify.security.CustomAuthenticationSuccessHandler;
import com.conder.groupify.security.CustomLogoutSuccessHandler;
import com.conder.groupify.security.CustomUsernamePasswordAuthenticationFilter;
import com.conder.groupify.security.jwt.JWTAuthorizationFilter;
import com.conder.groupify.security.jwt.JwtAuthenticationErrorHandler;
import com.conder.groupify.security.user.UserDetailsServiceImpl;
import com.conder.groupify.util.ReactRouterRoutes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
  private final JwtAuthenticationErrorHandler jwtAuthenticationErrorHandler;
  private final UserDetailsServiceImpl userDetailsService;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final CustomLogoutSuccessHandler customLogoutHandler;
  private final ApplicationUserRepository applicationUserRepository;

  @Value("${app.token.secret}")
  @Autowired
  private String SECRET;

  public SecurityConfig(
      CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler,
      JwtAuthenticationErrorHandler jwtAuthenticationErrorHandler,
      UserDetailsServiceImpl userDetailsService,
      BCryptPasswordEncoder bCryptPasswordEncoder,
      CustomLogoutSuccessHandler customLogoutSuccessHandler,
      ApplicationUserRepository applicationUserRepository) {

    this.customAuthenticationSuccessHandler = customAuthenticationSuccessHandler;
    this.jwtAuthenticationErrorHandler = jwtAuthenticationErrorHandler;
    this.userDetailsService = userDetailsService;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    this.applicationUserRepository = applicationUserRepository;
    this.customLogoutHandler = customLogoutSuccessHandler;
  }

  public CustomUsernamePasswordAuthenticationFilter customUsernamePasswordAuthenticationFilter()
      throws Exception {

    CustomUsernamePasswordAuthenticationFilter customUsernamePasswordAuthenticationFilter =
        new CustomUsernamePasswordAuthenticationFilter(authenticationManager());
    customUsernamePasswordAuthenticationFilter.setFilterProcessesUrl("/api/v1/auth/login");
    customUsernamePasswordAuthenticationFilter.setAuthenticationSuccessHandler(
        customAuthenticationSuccessHandler);
    return customUsernamePasswordAuthenticationFilter;
  }

  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    // h2 config
    http.headers().frameOptions().disable();

    // JWTs aren't subject to CSRF
    http.csrf().disable();
    http.cors()
        .and()
        .authorizeRequests()
        // Set path permissions
        .antMatchers("/**")
        .permitAll()
        .requestMatchers(PathRequest.toH2Console())
        .permitAll()
        .antMatchers(ReactRouterRoutes.LOGIN)
        .permitAll()
        .antMatchers(ReactRouterRoutes.REGISTER)
        .permitAll()
        .antMatchers(ReactRouterRoutes.UPLOAD_IMAGE)
        .permitAll()
        .antMatchers("/notFound")
        .permitAll()
        .antMatchers("/h2_console/**")
        .permitAll()
        .antMatchers(HttpMethod.GET, "/index.html")
        .permitAll()
        .antMatchers(HttpMethod.POST, "/api/v1/auth/login")
        .permitAll()
        .antMatchers(HttpMethod.GET, "/api/v1/auth/logout")
        .permitAll()
        .antMatchers(HttpMethod.OPTIONS, "api/v1/users/sign-up")
        .permitAll()
        .antMatchers(HttpMethod.POST, "/api/v1/users/sign-up")
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .exceptionHandling()
        .authenticationEntryPoint(jwtAuthenticationErrorHandler)
        .and()
        .addFilter(customUsernamePasswordAuthenticationFilter())
        .addFilter(
            new JWTAuthorizationFilter(applicationUserRepository, authenticationManager(), SECRET));

    http.logout()
        .addLogoutHandler(new SecurityContextLogoutHandler())
        .logoutUrl("/api/v1/auth/logout")
        .logoutSuccessHandler(customLogoutHandler)
        .clearAuthentication(true);
    //    try if probs
    //    HttpStatusReturningLogoutSuccessHandler
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring()
        .antMatchers(HttpMethod.POST, "api/v1/auth/login")
        .and()
        .ignoring()
        .antMatchers(
            "/",
            "/*.json",
            "/static/**",
            "/images/**",
            "/*.html",
            "/favicon.ico",
            "/**/*.html",
            "/**/*.css",
            "/**/*.js",
            "/service-worker.js");
  }
}
