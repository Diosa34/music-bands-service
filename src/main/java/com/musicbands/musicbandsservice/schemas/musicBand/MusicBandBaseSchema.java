package com.musicbands.musicbandsservice.schemas.musicBand;

import com.musicbands.musicbandsservice.models.MusicGenre;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MusicBandBaseSchema {
    @Schema(example = "My lovely music band")
    @NotBlank
    @NotNull
    private String name;

    @Schema(example = "10")
    @Positive
    private Long numberOfParticipants;

    @Schema(example = "Description of my lovely music band")
    private String description;

    @Schema(example = "ROCK")
    @NotNull
    private MusicGenre genre;
}

