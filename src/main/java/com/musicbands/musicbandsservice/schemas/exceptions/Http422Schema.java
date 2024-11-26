package com.musicbands.musicbandsservice.schemas.exceptions;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class Http422Schema {
    @Data
    @AllArgsConstructor
    public static class FieldErrorDescription {
        private String fieldName;
        private String errorMessage;
    }

    @JacksonXmlElementWrapper(useWrapping = false)
    private List<FieldErrorDescription> errors = new ArrayList<>();

    private Integer code = 422;

    public Http422Schema(Map<String, String> errors) {
        for (Map.Entry<String, String> error : errors.entrySet()) {
            this.errors.add(new FieldErrorDescription(error.getKey(), error.getValue()));
        }
    }
}
