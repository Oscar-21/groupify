package com.conder.groupify.util;

import org.junit.Test;

import static com.conder.groupify.util.SongKickConstants.baseApiUrl;
import static org.junit.Assert.assertEquals;

public class SongKickRoutesTest extends SongKickRoutes {

  private String secret = "secret";
  private String artist = "artist";

  @Test
  public void getArtistsUpcomingEventsUrl() {
    String artistId = "1A3B4D";
    assertEquals(
        baseApiUrl + "/artists/" + artistId + "/calendar.json?apikey=" + secret,
        getArtistsUpcomingEventsUrl(artistId, secret));
  }

  @Test
  public void searchArtist() {
    assertEquals(
        baseApiUrl + "/search/artists.json?apikey=" + secret + "&query=" + artist,
        searchArtist(artist, secret));
  }

  @Test
  public void searchArtistNextPage() {
    String page = "page";
    assertEquals(
        baseApiUrl + "/search/artists.json?apikey=" + secret + "&query=" + artist + "&page=" + page,
        searchArtistNextPage(artist, page, secret));
  }
}
