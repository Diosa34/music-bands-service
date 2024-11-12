package com.musicbands.musicbandsservice.services;

import com.musicbands.musicbandsservice.mappers.SingleMapper;
import com.musicbands.musicbandsservice.models.Single;
import com.musicbands.musicbandsservice.repositories.SingleRepository;
import com.musicbands.musicbandsservice.schemas.single.SingleBaseSchema;
import com.musicbands.musicbandsservice.schemas.single.SingleReadSchema;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SingleService {
    private final SingleRepository singleRepository;
    private final SingleMapper singleMapper;

    public SingleReadSchema add(Long bandId, SingleBaseSchema schema) {
        Single model = singleMapper.mapSingleBaseToEntity(bandId, schema);
        model = singleRepository.save(model);
        return singleMapper.mapEntityToSingleRead(model);
    }
}
