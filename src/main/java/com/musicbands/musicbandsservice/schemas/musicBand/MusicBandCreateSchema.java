package com.musicbands.musicbandsservice.schemas.musicBand;

import com.musicbands.musicbandsservice.models.Coordinates;
import com.musicbands.musicbandsservice.models.Label;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MusicBandCreateSchema extends MusicBandBaseSchema{
    @Schema(description = "Coordinates, полученные из БД")
    private Coordinates coordinates;

   @Schema(description = "Label, полученная из БД")
    private Label label;
}
