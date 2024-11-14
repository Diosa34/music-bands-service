package com.musicbands.musicbandsservice.services.mappers;

import com.musicbands.musicbandsservice.models.Coordinates;
import com.musicbands.musicbandsservice.repositories.CoordinatesRepository;
import com.musicbands.musicbandsservice.schemas.coordinates.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CoordinatesMapper {
    private final CoordinatesRepository coordinatesRepository;

    protected Coordinates mapCoordinatesBaseToEntity(CoordinatesBaseSchema schema, Coordinates entity) {
        entity.setX(schema.getX());
        entity.setY(schema.getY());
        return entity;
    }

    protected Coordinates mapCoordinatesBaseToEntity(CoordinatesBaseSchema schema) {
        Coordinates entity = new Coordinates();
        entity.setX(schema.getX());
        entity.setY(schema.getY());
        return entity;
    }

    public Coordinates mapCoordinatesCreateToEntity(CoordinatesCreateSchema schema) {
        Coordinates entity = new Coordinates();
        mapCoordinatesBaseToEntity(schema, entity);
        return entity;
    }

    public Coordinates mapCoordinatesUpdateToEntity(CoordinatesUpdateSchema schema, Coordinates entity) {
        mapCoordinatesBaseToEntity(schema, entity);
        return entity;
    }

    public Coordinates mapOrGetCoordinatesEntity(CoordinatesNestedSchema schema) {
        Coordinates coords;
        if (schema.getId() != null) {
            coords = coordinatesRepository.findById(schema.getId()).orElseThrow(
                    () -> new RuntimeException("Obj not found") // todo: insert exception
            );
        } else {
            coords = new Coordinates();
            coords.setX(schema.getX());
            coords.setY(schema.getY());
        }
        return coords;
    }

    public CoordinatesReadSchema mapEntityToCoordinatesRead(Coordinates entity) {
        CoordinatesReadSchema schema = new CoordinatesReadSchema();
        schema.setId(entity.getId());
        schema.setX(entity.getX());
        schema.setY(entity.getY());
        return schema;
    }
}
