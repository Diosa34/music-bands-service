package com.musicbands.musicbandsservice.repositories;

import com.musicbands.musicbandsservice.models.Label;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LabelRepository extends JpaRepository<Label, Long> {
    Optional<Label> findById(Long id);
}
