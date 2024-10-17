package com.musicbands.musicbandsservice.schemas.single;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class LabelReadSchema extends LabelBaseSchema{
    @Schema(example = "1")
    private Long id;
}
