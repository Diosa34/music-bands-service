package com.musicbands.musicbandsservice.schemas.label;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class LabelCreateSchema extends LabelBaseSchema {
}
