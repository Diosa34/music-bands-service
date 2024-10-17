package com.musicbands.musicbandsservice.schemas.label;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class LabelBaseSchema {
    @Schema(example = "National")
    private String name;
}
