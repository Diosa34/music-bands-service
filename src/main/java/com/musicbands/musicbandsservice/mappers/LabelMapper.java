package com.musicbands.musicbandsservice.mappers;

import com.musicbands.musicbandsservice.models.Label;
import com.musicbands.musicbandsservice.schemas.single.LabelReadSchema;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LabelMapper {
    public LabelReadSchema mapEntityToLabelRead(Label entity) {
        LabelReadSchema schema = new LabelReadSchema();
        schema.setId(entity.getId());
        schema.setName(entity.getName());
        return schema;
    }
}
