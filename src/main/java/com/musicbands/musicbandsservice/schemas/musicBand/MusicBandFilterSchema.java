package com.musicbands.musicbandsservice.schemas.musicBand;

import com.musicbands.musicbandsservice.models.MusicGenre;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.time.ZonedDateTime;

@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Getter
public class MusicBandFilterSchema {
    @Setter
    private String name;

    @Setter
    @Pattern(regexp = "^full|start$")
    private String nameMatchMode = "full";

    @Setter
    private Long coordinatesId;

    @Setter
    private ZonedDateTime creationDate;

    private Long numberOfParticipants; // can be null
    @Schema(hidden = true)
    private boolean numberOfParticipantsSet = false;

    private String description; // can be null
    @Schema(hidden = true)
    private boolean descriptionSet = false;

    @Setter
    private MusicGenre genre;

    private Long labelId; // can be null
    @Schema(hidden = true)
    private boolean labelSet = false;

    @Setter
    @Pattern(regexp = "^id|name|coordinates|creationDate|numberOfParticipants|description|genre|label$", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String orderBy = "id";

    @Setter
    @PositiveOrZero
    private Integer pageSize = 0;

    @Setter
    @PositiveOrZero
    private Integer page = 0;

    public void setNumberOfParticipants(Long numberOfParticipants) {
        this.numberOfParticipantsSet = true;
        this.numberOfParticipants = numberOfParticipants;
    }

    public void setDescription(String description) {
        this.descriptionSet = true;
        this.description = description;
    }

    public void setLabelId(Long labelId) {
        this.labelSet = true;
        this.labelId = labelId;
    }
}
