package com.musicbands.musicbandsservice.schemas.musicBand;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.musicbands.musicbandsservice.schemas.coordinates.CoordinatesNestedSchema;
import com.musicbands.musicbandsservice.schemas.label.LabelNestedSchema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class MusicBandUpdateSchema extends MusicBandBaseSchema {
    @NotNull
    @JacksonXmlElementWrapper(useWrapping = false)
    private CoordinatesNestedSchema coordinates;

    @NotNull
    @JacksonXmlElementWrapper(useWrapping = false)
    private LabelNestedSchema label;
}
