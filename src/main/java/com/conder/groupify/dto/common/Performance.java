package com.conder.groupify.dto.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Performance {
  private Integer id;
  private String displayName;
  private String billing;
  private Integer billingIndex;
  private Artist artist;
}
