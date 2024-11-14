package com.musicbands.musicbandsservice.services;

import com.musicbands.musicbandsservice.models.Coordinates;
import com.musicbands.musicbandsservice.repositories.CoordinatesRepository;
import com.musicbands.musicbandsservice.schemas.coordinates.CoordinatesCreateSchema;
import com.musicbands.musicbandsservice.schemas.coordinates.CoordinatesReadSchema;
import com.musicbands.musicbandsservice.schemas.coordinates.CoordinatesUpdateSchema;
import com.musicbands.musicbandsservice.services.mappers.CoordinatesMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class CoordinatesService {
    private final CoordinatesRepository coordinatesRepository;
    private final CoordinatesMapper coordinatesMapper;

    public CoordinatesReadSchema getCoordinates(Long id) {
        Coordinates coordinates = coordinatesRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Coordinates not found") // todo: insert exception
        );
        return coordinatesMapper.mapEntityToCoordinatesRead(coordinates);
    }

    public CoordinatesReadSchema createCoordinates(CoordinatesCreateSchema schema) {
        Coordinates model = coordinatesMapper.mapCoordinatesCreateToEntity(schema);
        model = coordinatesRepository.save(model);
        return coordinatesMapper.mapEntityToCoordinatesRead(model);
    }

    public CoordinatesReadSchema updateCoordinates(Long id, CoordinatesUpdateSchema schema) {
        Coordinates coordinates = coordinatesRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Coordinates not found") // todo: insert exception
        );
        coordinatesMapper.mapCoordinatesUpdateToEntity(schema, coordinates);
        coordinates = coordinatesRepository.save(coordinates);
        return coordinatesMapper.mapEntityToCoordinatesRead(coordinates);
    }

    public void deleteCoordinates(Long id) {
        coordinatesRepository.deleteById(id);
    }
}
