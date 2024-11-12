package com.musicbands.musicbandsservice.repositories;

import com.musicbands.musicbandsservice.models.Single;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SingleRepository extends JpaRepository<Single, Long> {
    Optional<Single> findById(Long id);
}
