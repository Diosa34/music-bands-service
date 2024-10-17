package com.musicbands.musicbandsservice.schemas;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GroupSchema {
    private String name;
	private Long number;
}
