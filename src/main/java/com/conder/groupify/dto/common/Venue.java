package com.conder.groupify.dto.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Venue {
  private Integer id;
  private String displayName;
  private String uri;
  private MetroArea metroArea;
  private Double lat;
  private Double lng;
}
