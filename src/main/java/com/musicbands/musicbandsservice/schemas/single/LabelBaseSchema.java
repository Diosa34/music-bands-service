package com.musicbands.musicbandsservice.schemas.single;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LabelBaseSchema {
    @Schema(example = "National")
    @NotNull
    private String name;
}
