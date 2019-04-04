package com.conder.groupify.config;

import com.conder.groupify.repository.ApplicationUserRepository;
import com.conder.groupify.repository.ArtistEntityRepository;
import com.conder.groupify.service.SongKickService;
import com.conder.groupify.service.SongKickServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

@Configuration
@Profile("dev")
public class SongKickServiceProductionConfig implements SongKickServiceConfig {

  private final ApplicationUserRepository applicationUserRepository;
  private final ArtistEntityRepository artistEntityRepository;
  private final RestTemplate restTemplate;

  public SongKickServiceProductionConfig(
      ApplicationUserRepository applicationUserRepository,
      ArtistEntityRepository artistEntityRepository,
      RestTemplate restTemplate) {
    this.applicationUserRepository = applicationUserRepository;
    this.artistEntityRepository = artistEntityRepository;
    this.restTemplate = restTemplate;
  }

  @Override
  @Bean
  public SongKickService songKickService() {
    return new SongKickServiceImpl(applicationUserRepository, artistEntityRepository, restTemplate);
  }
}
