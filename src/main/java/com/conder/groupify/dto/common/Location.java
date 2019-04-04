package com.conder.groupify.dto.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Location {
  private String city;
  private Double lat;
  private Double lng;
}
