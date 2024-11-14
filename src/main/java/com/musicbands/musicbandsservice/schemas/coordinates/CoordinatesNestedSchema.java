package com.musicbands.musicbandsservice.schemas.coordinates;

import com.musicbands.musicbandsservice.schemas.validation.OneAttributeIsSet;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
@OneAttributeIsSet.List({
        @OneAttributeIsSet(
                firstAttributeName = "id",
                secondAttributeName = "x"
        ),
        @OneAttributeIsSet(
                firstAttributeName = "id",
                secondAttributeName = "y"
        ),
})
public class CoordinatesNestedSchema {
    @Schema(example = "1")
    private Long id;

    @Schema(example = "1")
    @Min(-531)
    private Double x;

    @Schema(example = "1")
    private Long y;
}
