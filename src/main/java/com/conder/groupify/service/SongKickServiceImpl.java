package com.conder.groupify.service;

import com.conder.groupify.domain.ApplicationUser;
import com.conder.groupify.domain.ArtistEntity;
import com.conder.groupify.dto.ArtistSearch;
import com.conder.groupify.dto.ArtistsUpcomingEvents;
import com.conder.groupify.repository.ApplicationUserRepository;
import com.conder.groupify.repository.ArtistEntityRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

import static com.conder.groupify.util.SongKickRoutes.*;

@Service
public class SongKickServiceImpl implements SongKickService {

  private final ApplicationUserRepository applicationUserRepository;
  private final ArtistEntityRepository artistEntityRepository;
  private final RestTemplate restTemplate;

  public SongKickServiceImpl(
      ApplicationUserRepository applicationUserRepository,
      ArtistEntityRepository artistEntityRepository,
      RestTemplate restTemplate) {
    this.applicationUserRepository = applicationUserRepository;
    this.artistEntityRepository = artistEntityRepository;
    this.restTemplate = restTemplate;
  }

  public ArtistEntity addArtist(ArtistEntity artistEntity, ApplicationUser user) {
    ArtistEntity artist = artistEntityRepository.save(artistEntity);
    Set<ArtistEntity> artistEntitySet = user.getArtistEntities();
    artistEntitySet.add(artist);
    user.setArtistEntities(artistEntitySet);
    applicationUserRepository.save(user);
    return artist;
  }

  public ArtistSearch artistSearch(String artist, String song_kick_secret) {
    return restTemplate
        .getForEntity(searchArtist(artist, song_kick_secret), ArtistSearch.class)
        .getBody();
  }

  public ArtistsUpcomingEvents getUpcomingArtistsEvents(String artistId, String song_kick_secret) {
    return restTemplate
        .getForEntity(
            getArtistsUpcomingEventsUrl(artistId, song_kick_secret), ArtistsUpcomingEvents.class)
        .getBody();
  }

  public ArtistSearch artistSearchNextPage(
      String currentArtistSearchQuery, String page, String song_kick_secret) {
    return restTemplate
        .getForEntity(
            searchArtistNextPage(currentArtistSearchQuery, page, song_kick_secret),
            ArtistSearch.class)
        .getBody();
  }

  @Override
  public Set<ArtistEntity> getCurrentUserBands(ApplicationUser user) {
    return user.getArtistEntities();
  }

  @Override
  public Set<ArtistEntity> getCurrentUserBands() {
    ApplicationUser user = applicationUserRepository.findByEmail("austin.conder@outlook.com");
    return user.getArtistEntities();
  }
}
