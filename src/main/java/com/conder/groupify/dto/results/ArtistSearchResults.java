package com.conder.groupify.dto.results;

import com.conder.groupify.dto.common.Artist;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ArtistSearchResults {
  private List<Artist> artist = null;
}
