package com.musicbands.musicbandsservice.schemas.lists;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ListWithPaginatorSchema<T> { // todo: add swagger docs annotations
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<T> elements;
    private Long currentPage;
    private Long totalPages;
}
