package com.musicbands.musicbandsservice.schemas.coordinates;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CoordinatesReadSchema extends CoordinatesBaseSchema{
    @Schema(example = "1")
    private Long id;
}