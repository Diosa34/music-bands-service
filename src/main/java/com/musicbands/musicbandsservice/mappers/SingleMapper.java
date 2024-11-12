package com.musicbands.musicbandsservice.mappers;

import com.musicbands.musicbandsservice.exceptions.NotFoundException;
import com.musicbands.musicbandsservice.models.Single;
import com.musicbands.musicbandsservice.repositories.MusicBandRepository;
import com.musicbands.musicbandsservice.schemas.single.SingleBaseSchema;
import com.musicbands.musicbandsservice.schemas.single.SingleReadSchema;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SingleMapper {
    private final MusicBandRepository musicBandRepository;

    public SingleReadSchema mapEntityToSingleRead(Single entity) {
        SingleReadSchema schema = new SingleReadSchema();
        schema.setMusicBandId(entity.getMusicBand().getId());
        schema.setName(entity.getName());
        schema.setDescription(entity.getDescription());
        return schema;
    }

    public Single mapSingleBaseToEntity(Long bandId, SingleBaseSchema schema) {
        Single entity = new Single();
        entity.setMusicBand(
                musicBandRepository.findById(bandId).orElseThrow(
                        () -> new NotFoundException(bandId, "Музыкальная группа")
                ));
        entity.setName(schema.getName());
        entity.setDescription(schema.getDescription());
        return entity;
    }
}
