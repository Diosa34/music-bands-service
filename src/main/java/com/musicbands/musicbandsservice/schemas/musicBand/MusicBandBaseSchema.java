package com.musicbands.musicbandsservice.schemas.musicBand;

import com.musicbands.musicbandsservice.models.MusicGenre;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class MusicBandBaseSchema {
    @Schema(example = "My lovely music band")
    @NotBlank
    @NotNull
    @Size(min = 1)
    protected String name;

    @Schema(example = "70")
    @Positive
    protected Long numberOfParticipants;

    @Schema(example = "Description of my lovely music band")
    protected String description;

    @Schema(example = "ROCK")
    @NotNull
    protected MusicGenre genre;
}

