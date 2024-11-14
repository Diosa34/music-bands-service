package com.musicbands.musicbandsservice.schemas.label;


import com.musicbands.musicbandsservice.schemas.validation.OneAttributeIsSet;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
@OneAttributeIsSet(
        firstAttributeName = "id",
        secondAttributeName = "name"
)
public class LabelNestedSchema {
    private Long id;

    @Schema(example = "National") // todo: not empty
    private String name;
}
