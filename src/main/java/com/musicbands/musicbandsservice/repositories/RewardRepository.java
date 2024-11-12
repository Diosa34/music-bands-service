package com.musicbands.musicbandsservice.repositories;

import com.musicbands.musicbandsservice.models.Reward;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RewardRepository extends JpaRepository<Reward, Long> {
    Optional<Reward> findById(Long id);
}