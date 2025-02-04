package com.musicbands.musicbandsservice.schemas.musicBand;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.musicbands.musicbandsservice.schemas.coordinates.CoordinatesNestedSchema;
import com.musicbands.musicbandsservice.schemas.label.LabelNestedSchema;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class MusicBandCreateSchema extends MusicBandBaseSchema {
    @Valid
    @JacksonXmlElementWrapper(useWrapping = false)
    private CoordinatesNestedSchema coordinates;

    @Valid
    @JacksonXmlElementWrapper(useWrapping = false)
    private LabelNestedSchema label;
}
