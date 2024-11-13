package com.musicbands.musicbandsservice.exceptions.schemas;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class BadRequestSchema {
    @Schema(example = "400")
    private Integer code;
    @Schema(example = "Invalid request message framing, or deceptive request routing.")
    private String detail;

    public BadRequestSchema(IllegalArgumentException exception) {
        this.code = 400;
        this.setDetail(exception.getMessage());
    }
}
