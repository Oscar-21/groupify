package com.conder.groupify.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

import static com.conder.groupify.util.SongKickConstants.baseImageUrl;
import static com.conder.groupify.util.SongKickConstants.postFixImageUrl;

@Entity
@NoArgsConstructor
@Getter
public class ArtistEntity {

  @Id @Setter private String id;

  @Setter private String imageUrl;

  @Setter private String name;

  public void setImageUrl(String artistId) {
    imageUrl = baseImageUrl + artistId + postFixImageUrl;
  }
}
