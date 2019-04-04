package com.conder.groupify.dto.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Artist {
  private Integer id;
  private String displayName;
  private String uri;
  private List<Identifier> identifier = null;
}
