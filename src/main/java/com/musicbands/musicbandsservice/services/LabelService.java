package com.musicbands.musicbandsservice.services;

import com.musicbands.musicbandsservice.models.Label;
import com.musicbands.musicbandsservice.repositories.LabelRepository;
import com.musicbands.musicbandsservice.schemas.label.LabelCreateSchema;
import com.musicbands.musicbandsservice.schemas.label.LabelReadSchema;
import com.musicbands.musicbandsservice.schemas.label.LabelUpdateSchema;
import com.musicbands.musicbandsservice.services.mappers.LabelMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LabelService {
    private final LabelMapper labelMapper;
    private final LabelRepository labelRepository;

    public LabelReadSchema getLabel(Long labelId) {
        Label label = labelRepository.findById(labelId).orElseThrow(
                () -> new RuntimeException("Not found") // todo: exception
        );
        return labelMapper.mapEntityToLabelRead(label);
    }

    public LabelReadSchema createLabel(LabelCreateSchema schema) {
        Label label = labelMapper.mapCreateSchemaToEntity(schema);
        label = labelRepository.save(label);
        return labelMapper.mapEntityToLabelRead(label);
    }

    public LabelReadSchema updateLabel(Long labelId, LabelUpdateSchema schema) {
        Label label = labelRepository.findById(labelId).orElseThrow(
                () -> new RuntimeException("Not found") // todo: exception
        );
        labelMapper.mapUpdateSchemaToEntity(schema, label);
        label = labelRepository.save(label);
        return labelMapper.mapEntityToLabelRead(label);
    }

    public void deleteLabel(Long labelId) {
        labelRepository.deleteById(labelId); // todo: check
    }
}
