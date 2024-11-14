package com.musicbands.musicbandsservice.schemas.coordinates;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CoordinatesBaseSchema {
    @Schema(example = "1")
    @NotNull
    @Min(-531)
    private Double x;

    @Schema(example = "1")
    @NotNull
    private Long y;
}
