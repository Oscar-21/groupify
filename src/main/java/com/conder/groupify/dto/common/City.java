package com.conder.groupify.dto.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class City {
  Integer id;
  String displayName;
  String uri;
  Country country;
}
