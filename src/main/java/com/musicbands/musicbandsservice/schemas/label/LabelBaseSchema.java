package com.musicbands.musicbandsservice.schemas.label;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LabelBaseSchema {
    @Schema(example = "National")
    @NotBlank
    @NotNull
    private String name;
}
