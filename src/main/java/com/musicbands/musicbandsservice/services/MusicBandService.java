package com.musicbands.musicbandsservice.services;

import com.musicbands.musicbandsservice.exceptions.NotFoundException;
import com.musicbands.musicbandsservice.models.MusicBand;
import com.musicbands.musicbandsservice.repositories.CoordinatesRepository;
import com.musicbands.musicbandsservice.repositories.LabelRepository;
import com.musicbands.musicbandsservice.repositories.MusicBandRepository;
import com.musicbands.musicbandsservice.schemas.GroupSchema;
import com.musicbands.musicbandsservice.schemas.StatisticSchema;
import com.musicbands.musicbandsservice.schemas.lists.ListWithPaginatorSchema;
import com.musicbands.musicbandsservice.schemas.musicBand.MusicBandCreateSchema;
import com.musicbands.musicbandsservice.schemas.musicBand.MusicBandFilterSchema;
import com.musicbands.musicbandsservice.schemas.musicBand.MusicBandReadSchema;
import com.musicbands.musicbandsservice.schemas.musicBand.MusicBandUpdateSchema;
import com.musicbands.musicbandsservice.services.mappers.MusicBandMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class MusicBandService {
    private final MusicBandMapper musicBandMapper;
    private final MusicBandRepository musicBandRepository;
    private final CoordinatesRepository coordinatesRepository;
    private final LabelRepository labelRepository;

    protected static Stream<MusicBand> orderMusicBandBy(Stream<MusicBand> musicBandStream, String orderBy) {
        return switch (orderBy.toLowerCase()) {
            case "name" -> musicBandStream.sorted(Comparator.comparing(MusicBand::getName));
            case "coordinates" ->
                    musicBandStream.sorted(Comparator.comparingLong(musicBand -> musicBand.getCoordinates().getId()));
            case "creationdate" -> musicBandStream.sorted(Comparator.comparing(MusicBand::getCreationDate));
            case "numberofparticipants" ->
                    musicBandStream.sorted(Comparator.comparingLong(MusicBand::getNumberOfParticipants));
            case "genre" -> musicBandStream.sorted(Comparator.comparing(MusicBand::getGenre));
            case "label" ->
                    musicBandStream.sorted(Comparator.comparing(musicBand -> musicBand.getLabel() == null ? "" : musicBand.getLabel().getName()));
            default -> musicBandStream.sorted(Comparator.comparing(MusicBand::getId));
        };
    }

    public ListWithPaginatorSchema<MusicBandReadSchema> filterMusicBand(MusicBandFilterSchema filterSchema) {
        ListWithPaginatorSchema<MusicBandReadSchema> paginatorSchema = new ListWithPaginatorSchema<>();

        Stream<MusicBand> musicBandStream = musicBandRepository.findAll().stream();

        if (filterSchema.getName() != null) {
            if (filterSchema.getNameMatchMode().equals("full")) {
                musicBandStream = musicBandStream.filter(musicBand -> musicBand.getName().equals(filterSchema.getName()));
            } else if (filterSchema.getNameMatchMode().equals("start")) {
                musicBandStream = musicBandStream.filter(musicBand -> musicBand.getName().startsWith(filterSchema.getName()));
            }
        }

        if (filterSchema.getCoordinatesId() != null) {
            musicBandStream = musicBandStream.filter(
                    musicBand -> musicBand.getCoordinates().getId().equals(filterSchema.getCoordinatesId()));
        }

        if (filterSchema.getCreationDate() != null) {
            musicBandStream = musicBandStream.filter(
                    musicBand -> musicBand.getCreationDate().equals(filterSchema.getCreationDate()));
        }

        if (filterSchema.isNumberOfParticipantsSet()) {
            musicBandStream = musicBandStream.filter(
                    musicBand -> musicBand.getNumberOfParticipants().equals(filterSchema.getNumberOfParticipants()));
        }

        if (filterSchema.isDescriptionSet()) {
            musicBandStream = musicBandStream.filter(
                    musicBand -> musicBand.getDescription().equals(filterSchema.getDescription()));
        }

        if (filterSchema.isLabelSet()) {
            musicBandStream = musicBandStream.filter(
                    musicBand -> filterSchema.getLabelId() == null ?
                            musicBand.getLabel() == null :
                            musicBand.getLabel().getId().equals(filterSchema.getLabelId()));
        }

        musicBandStream = orderMusicBandBy(musicBandStream, filterSchema.getOrderBy());

        List<MusicBand> musicBandList = musicBandStream.toList();

        int totalElems = musicBandList.size();
        int elemsOnPage = filterSchema.getPageSize() == 0 ? totalElems : filterSchema.getPageSize();
        int page = filterSchema.getPageSize() == 0 ? 0 : filterSchema.getPage();
        int totalPages = elemsOnPage > 0 ? (int) Math.ceil((double) totalElems / elemsOnPage) : 1;



        if (page < totalPages) {
            int startIndex = page * elemsOnPage;
            int stopIndex = (page + 1) * elemsOnPage;

            if (page == totalPages - 1) {
                stopIndex = totalElems;
            }

            paginatorSchema.setElements(
                    musicBandList.subList(startIndex, stopIndex).stream()
                            .map(musicBandMapper::mapEntityToMusicBandRead)
                            .toList()
            );
        } else {
            paginatorSchema.setElements(new ArrayList<>());
        }

        paginatorSchema.setTotalPages(totalPages);
        paginatorSchema.setCurrentPage(page);
        paginatorSchema.setElementsOnPage(elemsOnPage);
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
                () -> new NotFoundException(musicBandId, "MusicBand")
        );
        return musicBandMapper.mapEntityToMusicBandRead(musicBand);
    }

    public MusicBandReadSchema updateMusicBand(Long musicBandId, MusicBandUpdateSchema schema) {
        MusicBand musicBand = musicBandRepository.findById(musicBandId).orElseThrow(
                () -> new NotFoundException(musicBandId, "MusicBand")
        );
        musicBandMapper.mapMusicBandUpdateToEntity(schema, musicBand);
        labelRepository.save(musicBand.getLabel());
        coordinatesRepository.save(musicBand.getCoordinates());
        musicBand = musicBandRepository.save(musicBand);
        return musicBandMapper.mapEntityToMusicBandRead(musicBand);
    }

    public void deleteMusicBand(Long musicBandId) {
        MusicBand musicBand = musicBandRepository.findById(musicBandId).orElseThrow(
                () -> new NotFoundException(musicBandId, "MusicBand")
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