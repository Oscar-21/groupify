package com.conder.groupify.service;

import com.conder.groupify.domain.ApplicationUser;
import com.conder.groupify.domain.ArtistEntity;
import com.conder.groupify.dto.ArtistSearch;
import com.conder.groupify.dto.ArtistsUpcomingEvents;
import com.conder.groupify.repository.ApplicationUserRepository;
import com.conder.groupify.repository.ArtistEntityRepository;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.util.Set;

import static com.conder.groupify.util.GroupifyUtils.artistSearchString;
import static com.conder.groupify.util.GroupifyUtils.artistUpcomingEventsString;

@Service
public class SongKickServiceTestImpl implements SongKickService {

  private final ApplicationUserRepository applicationUserRepository;
  private final ArtistEntityRepository artistEntityRepository;
  private final Gson gson = new Gson();

  public SongKickServiceTestImpl(
      ApplicationUserRepository applicationUserRepository,
      ArtistEntityRepository artistEntityRepository) {
    this.applicationUserRepository = applicationUserRepository;
    this.artistEntityRepository = artistEntityRepository;
  }

  public ArtistEntity addArtist(ArtistEntity artistEntity, ApplicationUser user) {

    ArtistEntity artist = artistEntityRepository.save(artistEntity);
    ApplicationUser currentUser = new ApplicationUser();
    currentUser.setEmail("dude");
    currentUser.setPassword("password");
    currentUser.setId(Long.valueOf(1));
    Set<ArtistEntity> artistEntitySet = currentUser.getArtistEntities();
    artistEntitySet.add(artist);
    currentUser.setArtistEntities(artistEntitySet);
    applicationUserRepository.save(currentUser);
    return artist;
  }

  public ArtistSearch artistSearch(String artist, String song_kick_secret) {
    return gson.fromJson(artistSearchString(), ArtistSearch.class);
  }

  public ArtistsUpcomingEvents getUpcomingArtistsEvents(String artistId, String song_kick_secret) {
    return gson.fromJson(artistUpcomingEventsString(), ArtistsUpcomingEvents.class);
  }

  public ArtistSearch artistSearchNextPage(
      String currentArtistSearchQuery, String page, String song_kick_secret) {
    return gson.fromJson(artistSearchString(), ArtistSearch.class);
  }

  @Override
  public Set<ArtistEntity> getCurrentUserBands(ApplicationUser user) {
    return null;
  }

  @Override
  public Set<ArtistEntity> getCurrentUserBands() {
    return null;
  }
}
