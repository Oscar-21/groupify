package com.conder.groupify.dto.resultspage;

import com.conder.groupify.dto.results.ArtistUpcomingEventsResults;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ArtistUpcomingEventsResultsPage extends ResultsPage {
  private ArtistUpcomingEventsResults results;
}
