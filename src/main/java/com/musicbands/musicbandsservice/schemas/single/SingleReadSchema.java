package com.musicbands.musicbandsservice.schemas.single;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SingleReadSchema extends SingleBaseSchema{
    @Schema(example = "1")
    @NotNull
    private Long musicBandId;
}
