package com.conder.groupify.controller;

import com.conder.groupify.domain.ApplicationUser;
import com.conder.groupify.domain.ArtistEntity;
import com.conder.groupify.dto.ArtistSearch;
import com.conder.groupify.dto.ArtistsUpcomingEvents;
import com.conder.groupify.service.SongKickService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/songkick")
public class SongKickController {

  private final SongKickService songKickService;
  private final RestTemplate restTemplate;
  private final String SONG_KICK_SECRET;
  private @Setter String currentArtistSearchQuery;

  public SongKickController(
      SongKickService songKickService,
      RestTemplate restTemplate,
      @Value("${app.songkick.secret}") String song_kick_secret) {
    this.songKickService = songKickService;
    this.restTemplate = restTemplate;
    SONG_KICK_SECRET = song_kick_secret;
  }

  @GetMapping("/upcoming-artists-events")
  ArtistsUpcomingEvents getUpcomingArtistsEvents(@Param("artistId") String artistId) {
    return songKickService.getUpcomingArtistsEvents(artistId, SONG_KICK_SECRET);
  }

  @GetMapping("/artist-search")
  ArtistSearch artistSearch(@Param("artist") String artist) {
    setCurrentArtistSearchQuery(artist);
    return songKickService.artistSearch(artist, SONG_KICK_SECRET);
  }

  @GetMapping("/artist-search-next-page")
  ArtistSearch artistSearchNextPage(@Param("page") String page) {
    return songKickService.artistSearchNextPage(currentArtistSearchQuery, page, SONG_KICK_SECRET);
  }

  @GetMapping("/add-artist")
  ArtistEntity addArtist(
      @Param("artistId") String artistId,
      @Param("artistName") String artistName,
      @AuthenticationPrincipal ApplicationUser user) {
    ArtistEntity artistEntity = new ArtistEntity();
    artistEntity.setId(artistId);
    artistEntity.setImageUrl(artistId);
    artistEntity.setName(artistName);
    return this.songKickService.addArtist(artistEntity, user);
  }

  @GetMapping("/my-bands")
  Set<ArtistEntity> myArtists(@AuthenticationPrincipal ApplicationUser user) {
    return this.songKickService.getCurrentUserBands(user);
  }

  @GetMapping("/my-bands-2")
  Set<ArtistEntity> myArtistsTwo() {
    return this.songKickService.getCurrentUserBands();
  }
}
