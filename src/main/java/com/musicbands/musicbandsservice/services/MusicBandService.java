package com.musicbands.musicbandsservice.services;

import com.musicbands.musicbandsservice.mappers.MusicBandMapper;
import com.musicbands.musicbandsservice.repositories.MusicBandRepository;
import com.musicbands.musicbandsservice.schemas.musicBand.MusicBandReadSchema;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MusicBandService {
    private final MusicBandRepository musicBandRepository;
    private final MusicBandMapper musicBandMapper;

    public Set<MusicBandReadSchema> getAll() {
        return musicBandRepository.findAll().stream()
                .map(musicBandMapper::mapEntityToMusicBandRead)
                .collect(Collectors.toSet());
    }
}