package com.musicbands.musicbandsservice.services;

import com.musicbands.musicbandsservice.mappers.CoordinatesMapper;
import com.musicbands.musicbandsservice.models.Coordinates;
import com.musicbands.musicbandsservice.repositories.CoordinatesRepository;
import com.musicbands.musicbandsservice.schemas.coordinates.CoordinatesCreateSchema;
import com.musicbands.musicbandsservice.schemas.coordinates.CoordinatesReadSchema;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CoordinatesService {
    private final CoordinatesRepository coordinatesRepository;
    private final CoordinatesMapper coordinatesMapper;

    public CoordinatesReadSchema add(CoordinatesCreateSchema schema) {
        Coordinates model = coordinatesMapper.mapCoordinatesCreateToEntity(schema);
        model = coordinatesRepository.save(model);
        return coordinatesMapper.mapEntityToCoordinatesRead(model);
    }
}
