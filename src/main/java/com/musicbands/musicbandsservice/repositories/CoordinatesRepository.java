package com.musicbands.musicbandsservice.repositories;

import com.musicbands.musicbandsservice.models.Coordinates;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CoordinatesRepository extends JpaRepository<Coordinates, Long> {
    Optional<Coordinates> findById(Long id);
}
