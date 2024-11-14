package com.musicbands.musicbandsservice.schemas.musicBand;

import com.musicbands.musicbandsservice.schemas.coordinates.CoordinatesReadSchema;
import com.musicbands.musicbandsservice.schemas.label.LabelReadSchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class MusicBandReadSchema extends MusicBandBaseSchema {
    @Schema(example = "1")
    private Long id;

    private CoordinatesReadSchema coordinates;

    @Schema(example = "70")
    @Positive
    private Long numberOfParticipants;

    @Schema(example = "2022-09-19T00:00:10Z")
    @NotNull
    private ZonedDateTime creationDate;

    private LabelReadSchema label;
}
