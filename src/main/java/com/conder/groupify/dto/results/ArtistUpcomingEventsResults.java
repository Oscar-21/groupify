package com.conder.groupify.dto.results;

import com.conder.groupify.dto.common.Event;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ArtistUpcomingEventsResults {
  private List<Event> event = null;
}
