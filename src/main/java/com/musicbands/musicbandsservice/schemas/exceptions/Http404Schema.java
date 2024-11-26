package com.musicbands.musicbandsservice.schemas.exceptions;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Http404Schema {
    @Schema(example = "404")
    private Integer code = 404;

    private String reason;

    public Http404Schema(String reason) {
        this.reason = reason;
    }
}
