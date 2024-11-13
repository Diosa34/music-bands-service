package com.musicbands.musicbandsservice.exceptions.schemas;

import com.musicbands.musicbandsservice.exceptions.NotFoundException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ObjectNotFoundSchema{
    @Schema(example = "404")
    private Integer code;
    @Schema(example = "Object not found")
    private String detail;

    public ObjectNotFoundSchema(NotFoundException exception) {
        this.code = exception.getCode();
        this.setDetail(exception.getEntityName() + " с идентификатором " + exception.getEntityID() + " не найдена");
    }
}
