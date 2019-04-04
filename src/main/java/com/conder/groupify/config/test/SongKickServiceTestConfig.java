package com.conder.groupify.config.test;

import com.conder.groupify.config.SongKickServiceConfig;
import com.conder.groupify.repository.ApplicationUserRepository;
import com.conder.groupify.repository.ArtistEntityRepository;
import com.conder.groupify.service.SongKickService;
import com.conder.groupify.service.SongKickServiceTestImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class SongKickServiceTestConfig implements SongKickServiceConfig {

  private final ApplicationUserRepository applicationUserRepository;
  private final ArtistEntityRepository artistEntityRepository;

  public SongKickServiceTestConfig(
      ApplicationUserRepository applicationUserRepository,
      ArtistEntityRepository artistEntityRepository) {
    this.applicationUserRepository = applicationUserRepository;
    this.artistEntityRepository = artistEntityRepository;
  }

  @Override
  @Bean
  public SongKickService songKickService() {
    return new SongKickServiceTestImpl(applicationUserRepository, artistEntityRepository);
  }
}
