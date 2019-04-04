package com.conder.groupify.dto;

import com.conder.groupify.dto.resultspage.ArtistSearchResultsPage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ArtistSearch {
  private ArtistSearchResultsPage resultsPage;
}
