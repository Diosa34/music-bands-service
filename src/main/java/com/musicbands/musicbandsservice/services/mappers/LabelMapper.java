package com.musicbands.musicbandsservice.services.mappers;

import com.musicbands.musicbandsservice.models.Label;
import com.musicbands.musicbandsservice.repositories.LabelRepository;
import com.musicbands.musicbandsservice.schemas.label.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LabelMapper {
    private final LabelRepository labelRepository;

    protected Label mapBaseSchemaToEntity(LabelBaseSchema schema, Label entity) {
        entity.setName(schema.getName());
        return entity;
    }

    protected Label mapBaseSchemaToEntity(LabelBaseSchema schema) {
        Label entity = new Label();
        return mapBaseSchemaToEntity(schema, entity);
    }

    public Label mapCreateSchemaToEntity(LabelCreateSchema schema) {
        return mapBaseSchemaToEntity(schema);
    }

    public Label mapUpdateSchemaToEntity(LabelUpdateSchema schema, Label entity) {
        return mapBaseSchemaToEntity(schema, entity);
    }

    public Label mapOrGetLabelEntity(LabelNestedSchema schema) {
        Label label;
        if (schema.getId() != null) {
            label = labelRepository.findById(schema.getId()).orElseThrow(
                    () -> new RuntimeException("Obj not found") // todo: insert exception
            );
        } else {
            label = new Label();
            label.setName(schema.getName());
        }
        return label;
    }

    public LabelReadSchema mapEntityToLabelRead(Label entity) {
        LabelReadSchema schema = new LabelReadSchema();
        schema.setId(entity.getId());
        schema.setName(entity.getName());
        return schema;
    }
}
