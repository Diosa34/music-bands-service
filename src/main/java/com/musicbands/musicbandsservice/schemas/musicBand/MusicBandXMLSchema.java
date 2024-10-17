package com.musicbands.musicbandsservice.schemas.musicBand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MusicBandXMLSchema extends MusicBandBaseSchema{
    @Schema(description = "ID имеющихся в БД координат")
    private Long coordinatesID;

    @Schema(description = "X новых, ещё не добавленных в БД coordinates", example = "1")
    private Double x;

    @Schema(description = "Y новых, ещё не добавленных в БД coordinates", example = "1")
    private Long y;

    @Schema(description = "ID имеющейся в БД label", example = "1")
    private Long labelId;

    @Schema(description = "Name новой, ещё не добавленной в БД label", example = "National")
    private String labelName;
}

