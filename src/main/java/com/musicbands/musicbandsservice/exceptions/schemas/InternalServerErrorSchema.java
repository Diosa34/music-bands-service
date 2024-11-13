package com.musicbands.musicbandsservice.exceptions.schemas;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.rmi.ServerException;

@Data
public class InternalServerErrorSchema{
    @Schema(example = "500")
    private Integer code;
    @Schema(example = "Something went wrong...")
    private String detail;

    public InternalServerErrorSchema(ServerException exception) {
        this.code = 500;
        this.setDetail(exception.getMessage());
    }
}
