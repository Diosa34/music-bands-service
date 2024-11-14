package com.musicbands.musicbandsservice.services;

import com.musicbands.musicbandsservice.models.MusicBand;
import com.musicbands.musicbandsservice.repositories.CoordinatesRepository;
import com.musicbands.musicbandsservice.repositories.LabelRepository;
import com.musicbands.musicbandsservice.repositories.MusicBandRepository;
import com.musicbands.musicbandsservice.schemas.GroupSchema;
import com.musicbands.musicbandsservice.schemas.StatisticSchema;
import com.musicbands.musicbandsservice.schemas.lists.ListWithPaginatorSchema;
import com.musicbands.musicbandsservice.schemas.musicBand.MusicBandCreateSchema;
import com.musicbands.musicbandsservice.schemas.musicBand.MusicBandReadSchema;
import com.musicbands.musicbandsservice.schemas.musicBand.MusicBandUpdateSchema;
import com.musicbands.musicbandsservice.services.mappers.MusicBandMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MusicBandService {
    private final MusicBandMapper musicBandMapper;
    private final MusicBandRepository musicBandRepository;
    private final CoordinatesRepository coordinatesRepository;
    private final LabelRepository labelRepository;

    public ListWithPaginatorSchema<MusicBandReadSchema> filterMusicBand() { // todo: refactor
        ListWithPaginatorSchema<MusicBandReadSchema> paginatorSchema = new ListWithPaginatorSchema<>();
        paginatorSchema.setElements(
                musicBandRepository.findAll().stream()
                        .map(musicBandMapper::mapEntityToMusicBandRead)
                        .collect(Collectors.toList())
        );
        return paginatorSchema;
    }

    public MusicBandReadSchema createMusicBand(MusicBandCreateSchema schema) {
        MusicBand musicBand = musicBandMapper.mapMusicBandCreateToEntity(schema);
        labelRepository.save(musicBand.getLabel());
        coordinatesRepository.save(musicBand.getCoordinates());
        musicBand = musicBandRepository.save(musicBand); // todo: check saving
        return musicBandMapper.mapEntityToMusicBandRead(musicBand);
    }

    public MusicBandReadSchema getMusicBand(Long musicBandId) {
        MusicBand musicBand = musicBandRepository.findById(musicBandId).orElseThrow(
                () -> new RuntimeException("d") // todo: exception
        );
        return musicBandMapper.mapEntityToMusicBandRead(musicBand);
    }

    public MusicBandReadSchema updateMusicBand(Long musicBandId, MusicBandUpdateSchema schema) {
        MusicBand musicBand = musicBandRepository.findById(musicBandId).orElseThrow(
                () -> new RuntimeException("d") // todo: exception
        );
        musicBandMapper.mapMusicBandUpdateToEntity(schema, musicBand);
        labelRepository.save(musicBand.getLabel());
        coordinatesRepository.save(musicBand.getCoordinates());
        musicBand = musicBandRepository.save(musicBand);
        return musicBandMapper.mapEntityToMusicBandRead(musicBand);
    }

    public void deleteMusicBand(Long musicBandId) {
        MusicBand musicBand = musicBandRepository.findById(musicBandId).orElseThrow(
                () -> new RuntimeException("d") // todo: exception
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