package com.musicbands.musicbandsservice.services;

import com.musicbands.musicbandsservice.mappers.MusicBandMapper;
import com.musicbands.musicbandsservice.models.MusicBand;
import com.musicbands.musicbandsservice.repositories.MusicBandRepository;
import com.musicbands.musicbandsservice.schemas.musicBand.*;
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

    public MusicBandReadSchema add(MusicBandXMLSchema schema) {
        MusicBand model = musicBandMapper.mapMusicBandXMLToEntity(schema);
        model = musicBandRepository.save(model);
        return musicBandMapper.mapEntityToMusicBandRead(model);
    }
}