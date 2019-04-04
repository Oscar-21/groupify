package com.conder.groupify.util;

import static com.conder.groupify.util.SongKickConstants.baseApiUrl;

public class SongKickRoutes {

  public static String getArtistsUpcomingEventsUrl(String artistId, String secret) {
    return baseApiUrl + "/artists/" + artistId + "/calendar.json?apikey=" + secret;
  }

  public static String searchArtist(String artist, String secret) {
    return baseApiUrl + "/search/artists.json?apikey=" + secret + "&query=" + artist;
  }

  public static String searchArtistNextPage(String artist, String page, String secret) {
    return baseApiUrl
        + "/search/artists.json?apikey="
        + secret
        + "&query="
        + artist
        + "&page="
        + page;
  }
}
