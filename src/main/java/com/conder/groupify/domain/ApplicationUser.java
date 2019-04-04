package com.conder.groupify.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
public class ApplicationUser {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private String email;

  private String password;

  @OneToMany(fetch = FetchType.EAGER)
  private Set<ArtistEntity> artistEntities = new HashSet<>();

  private Integer travelRadius = 0;
}
