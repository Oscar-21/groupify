package com.conder.groupify.dto.resultspage;

import com.conder.groupify.dto.results.ArtistSearchResults;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ArtistSearchResultsPage extends ResultsPage {
  private ArtistSearchResults results;
}
