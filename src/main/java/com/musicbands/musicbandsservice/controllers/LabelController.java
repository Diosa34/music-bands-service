package com.musicbands.musicbandsservice.controllers;

import com.musicbands.musicbandsservice.schemas.exceptions.Http404Schema;
import com.musicbands.musicbandsservice.schemas.exceptions.Http422Schema;
import com.musicbands.musicbandsservice.schemas.label.LabelCreateSchema;
import com.musicbands.musicbandsservice.schemas.label.LabelReadSchema;
import com.musicbands.musicbandsservice.schemas.label.LabelUpdateSchema;
import com.musicbands.musicbandsservice.services.LabelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Лэйблы (Labels)")
@RestController
@RequestMapping(value = "/labels", produces = MediaType.APPLICATION_XML_VALUE)
@AllArgsConstructor
@Validated
public class LabelController {
    private final LabelService labelService;

    @Operation(summary = "Получить лейбл")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Объект не найден", content = @Content(schema = @Schema(implementation = Http404Schema.class))),
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public LabelReadSchema getLabel(@PathVariable("id") Long labelId) {
        return labelService.getLabel(labelId);
    }


    @Operation(summary = "Добавить лейбл")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "422", description = "Валидационная ошибка", content = @Content(schema = @Schema(implementation = Http422Schema.class))),
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "", consumes = MediaType.APPLICATION_XML_VALUE)
    public LabelReadSchema createLabel(@RequestBody @Valid LabelCreateSchema schemaf) {
        return labelService.createLabel(schemaf);
    }


    @Operation(summary = "Изменить лейбл")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Объект не найден", content = @Content(schema = @Schema(implementation = Http404Schema.class))),
            @ApiResponse(responseCode = "422", description = "Валидационная ошибка", content = @Content(schema = @Schema(implementation = Http422Schema.class))),
    })
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_XML_VALUE)
    public LabelReadSchema updateLabel(@PathVariable("id") Long labelId, @RequestBody @Valid LabelUpdateSchema schema){
        return labelService.updateLabel(labelId, schema);
    }


    @Operation(summary = "Удалить лейбл")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Объект не найден", content = @Content(schema = @Schema(implementation = Http404Schema.class))),
    })
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(path = "/{id}") // todo: check type
    private void deleteLabel(@PathVariable("id") Long labelId){
        labelService.deleteLabel(labelId);
    }
}
