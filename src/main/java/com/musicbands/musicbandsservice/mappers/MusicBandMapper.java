package com.musicbands.musicbandsservice.mappers;

import com.musicbands.musicbandsservice.models.MusicBand;
import com.musicbands.musicbandsservice.schemas.musicBand.MusicBandReadSchema;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MusicBandMapper {
    private final CoordinatesMapper coordinatesMapper;
    private final LabelMapper labelMapper;
    public MusicBandReadSchema mapEntityToMusicBandRead(MusicBand entity) {
        MusicBandReadSchema schema = new MusicBandReadSchema();
        schema.setId(entity.getId());
        schema.setName(entity.getName());
        schema.setCoordinates(
                coordinatesMapper.mapEntityToCoordinatesRead(entity.getCoordinates())
        );
        schema.setNumberOfParticipants(entity.getNumberOfParticipants());
        schema.setDescription(entity.getDescription());
        schema.setGenre(entity.getGenre());
        schema.setCreationDate(entity.getCreationDate());
        schema.setLabel(
                labelMapper.mapEntityToLabelRead(entity.getLabel())
        );
        return schema;
    }
}
