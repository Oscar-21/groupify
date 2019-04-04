package com.conder.groupify.dto.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Event {
  private Integer id;
  private String displayName;
  private String type;
  private String uri;
  private String status;
  private Double popularity;
  private Start start;
  private List<Performance> performance = null;
  private Boolean flaggedAsEnded;
  private Venue venue;
  private Location location;
}
