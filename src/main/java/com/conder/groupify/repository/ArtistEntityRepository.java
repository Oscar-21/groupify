package com.conder.groupify.repository;

import com.conder.groupify.domain.ArtistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistEntityRepository extends JpaRepository<ArtistEntity, String> {}
