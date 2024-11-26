package com.musicbands.musicbandsservice.schemas.exceptions;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Http503Schema {
    @Schema(example = "503")
    private Integer code = 503;

    private String reason;

    public Http503Schema(String reason) {
        this.reason = reason;
    }
}
