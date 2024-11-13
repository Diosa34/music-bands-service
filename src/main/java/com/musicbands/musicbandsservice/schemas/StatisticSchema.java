package com.musicbands.musicbandsservice.schemas;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StatisticSchema {
    @Schema(example = "20")
    private Long average;
}
