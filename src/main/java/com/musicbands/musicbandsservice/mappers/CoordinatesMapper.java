package com.musicbands.musicbandsservice.mappers;

import com.musicbands.musicbandsservice.models.Coordinates;
import com.musicbands.musicbandsservice.schemas.coordinates.CoordinatesReadSchema;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CoordinatesMapper {
    public CoordinatesReadSchema mapEntityToCoordinatesRead(Coordinates entity) {
        CoordinatesReadSchema schema = new CoordinatesReadSchema();
        schema.setId(entity.getId());
        schema.setX(entity.getX());
        schema.setY(entity.getY());
        return schema;
    }
}
