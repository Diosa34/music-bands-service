package com.musicbands.musicbandsservice.schemas.musicBand;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MusicBandCreateSchema extends MusicBandBaseSchema{
    @Schema(description = "Координаты", example = "1")
    @NotNull
    @Positive
    private Long coordinatesId;

    @Schema(description = "National", example = "1")
    @Positive
    private Long labelId;
}
