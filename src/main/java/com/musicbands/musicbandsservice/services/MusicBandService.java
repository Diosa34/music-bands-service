package com.musicbands.musicbandsservice.services;

import com.musicbands.musicbandsservice.exceptions.NotFoundException;
import com.musicbands.musicbandsservice.mappers.MusicBandMapper;
import com.musicbands.musicbandsservice.models.MusicBand;
import com.musicbands.musicbandsservice.repositories.MusicBandRepository;
import com.musicbands.musicbandsservice.schemas.GroupSchema;
import com.musicbands.musicbandsservice.schemas.StatisticSchema;
import com.musicbands.musicbandsservice.schemas.musicBand.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
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

    public MusicBandReadSchema getById(Long id) {
        MusicBand musicBand = musicBandRepository.findById(id).orElseThrow(
                () -> new NotFoundException(id, "Музыкальная группа")
        );
        return musicBandMapper.mapEntityToMusicBandRead(musicBand);
    }

    public MusicBandReadSchema update(Long id, MusicBandXMLSchema schema) {
        MusicBand model = musicBandMapper.mapMusicBandUpdateToEntity(id, schema);
        model = musicBandRepository.save(model);
        return musicBandMapper.mapEntityToMusicBandRead(model);
    }

    public void delete(Long id) {
        MusicBand musicBand = musicBandRepository.findById(id).orElseThrow(
                () -> new NotFoundException(id, "Музыкальная группа")
        );
        musicBandRepository.delete(musicBand);
    }

    public StatisticSchema getStatistic() {
        return new StatisticSchema(musicBandRepository.findAll().stream()
                .mapToLong(MusicBand::getNumberOfParticipants) // Извлекаем цены
                .sum());
    }

    public List<GroupSchema> getGroups() {
        Map<String, Long> map = musicBandRepository.findAll().stream()
                .collect(
                        Collectors.groupingBy(
                                MusicBand::getName, Collectors.counting()
                        )
                );
        return map.entrySet()
                .stream()
                .map(entry -> new GroupSchema(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }
}