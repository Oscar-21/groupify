package com.conder.groupify.service;

import com.conder.groupify.domain.ApplicationUser;
import com.conder.groupify.domain.ArtistEntity;
import com.conder.groupify.dto.ArtistSearch;
import com.conder.groupify.dto.ArtistsUpcomingEvents;

import java.util.Set;

public interface SongKickService {
  ArtistEntity addArtist(ArtistEntity artistEntity, ApplicationUser user);

  ArtistsUpcomingEvents getUpcomingArtistsEvents(String artistId, String song_kick_secret);

  ArtistSearch artistSearch(String artist, String song_kick_secret);

  ArtistSearch artistSearchNextPage(
      String currentArtistSearchQuery, String page, String song_kick_secret);

  Set<ArtistEntity> getCurrentUserBands(ApplicationUser user);

    Set<ArtistEntity> getCurrentUserBands();
}
