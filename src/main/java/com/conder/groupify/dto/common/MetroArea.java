package com.conder.groupify.dto.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MetroArea {
  private String displayName;
  private Country country;
  private State state;
  private Integer id;
  private String uri;
}
