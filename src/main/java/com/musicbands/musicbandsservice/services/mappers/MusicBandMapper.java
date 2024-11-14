package com.musicbands.musicbandsservice.services.mappers;


import com.musicbands.musicbandsservice.models.MusicBand;
import com.musicbands.musicbandsservice.schemas.musicBand.MusicBandBaseSchema;
import com.musicbands.musicbandsservice.schemas.musicBand.MusicBandCreateSchema;
import com.musicbands.musicbandsservice.schemas.musicBand.MusicBandReadSchema;
import com.musicbands.musicbandsservice.schemas.musicBand.MusicBandUpdateSchema;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MusicBandMapper {
    private final CoordinatesMapper coordinatesMapper;
    private final LabelMapper labelMapper;

    protected MusicBand mapMusicBandBaseToEntity(MusicBandBaseSchema schema, MusicBand entity) {
        entity.setName(schema.getName());
        entity.setNumberOfParticipants(schema.getNumberOfParticipants());
        entity.setDescription(schema.getDescription());
        entity.setGenre(schema.getGenre());
        return entity;
    }

    protected MusicBand mapMusicBandBaseToEntity(MusicBandBaseSchema schema) {
        MusicBand musicBand = new MusicBand();
        return mapMusicBandBaseToEntity(schema, musicBand);
    }

    public MusicBand mapMusicBandCreateToEntity(MusicBandCreateSchema schema) {
        MusicBand entity = mapMusicBandBaseToEntity(schema);
        entity.setCoordinates(
                coordinatesMapper.mapOrGetCoordinatesEntity(
                        schema.getCoordinates()));
        entity.setLabel(
                labelMapper.mapOrGetLabelEntity(
                        schema.getLabel()));
        return entity;
    }

    public MusicBand mapMusicBandUpdateToEntity(MusicBandUpdateSchema schema, MusicBand entity) {
        mapMusicBandBaseToEntity(schema, entity);
        entity.setCoordinates(
                coordinatesMapper.mapOrGetCoordinatesEntity(
                        schema.getCoordinates()));
        entity.setLabel(
                labelMapper.mapOrGetLabelEntity(
                        schema.getLabel()));
        return entity;
    }

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
                labelMapper.mapEntityToLabelRead(entity.getLabel()));
        return schema;
    }
}
