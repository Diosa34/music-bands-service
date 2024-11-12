package com.musicbands.musicbandsservice.schemas.single;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class SingleBaseSchema {
    @Schema(example = "Awesome single")
    private String name;

    @Schema(example = "Uplifting and introspective anthem.")
    private String description;
}

