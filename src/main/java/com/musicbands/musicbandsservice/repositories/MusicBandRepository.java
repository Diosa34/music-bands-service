package com.musicbands.musicbandsservice.repositories;

import com.musicbands.musicbandsservice.models.MusicBand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MusicBandRepository extends JpaRepository<MusicBand, Long> {
    Optional<MusicBand> findById(Long id);
}
