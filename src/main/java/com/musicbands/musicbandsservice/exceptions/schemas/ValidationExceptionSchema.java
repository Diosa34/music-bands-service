package com.musicbands.musicbandsservice.exceptions.schemas;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.web.bind.MethodArgumentNotValidException;

@Data
public class ValidationExceptionSchema {
    @Schema(example = "422")
    private Integer code;
    @Schema(example = "Validation error")
    private String detail;

    public ValidationExceptionSchema(MethodArgumentNotValidException exception) {
        this.code = 422;
        this.setDetail(exception.getMessage());
    }
}
