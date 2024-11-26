package com.musicbands.musicbandsservice.controllers;

import com.musicbands.musicbandsservice.schemas.coordinates.CoordinatesCreateSchema;
import com.musicbands.musicbandsservice.schemas.coordinates.CoordinatesReadSchema;
import com.musicbands.musicbandsservice.schemas.coordinates.CoordinatesUpdateSchema;
import com.musicbands.musicbandsservice.schemas.exceptions.Http404Schema;
import com.musicbands.musicbandsservice.schemas.exceptions.Http422Schema;
import com.musicbands.musicbandsservice.services.CoordinatesService;
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

@Tag(name = "Координаты (Coordinates)")
@Validated
@RestController
@RequestMapping(value = "/coordinates", produces = MediaType.APPLICATION_XML_VALUE)
@AllArgsConstructor
public class CoordinatesController {
    private final CoordinatesService coordinatesService;

    @Operation(summary = "Получить координаты")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Объект не найден", content = @Content(schema = @Schema(implementation = Http404Schema.class))),
            @ApiResponse(responseCode = "422", description = "Валидационная ошибка", content = @Content(schema = @Schema(implementation = Http422Schema.class))),
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public CoordinatesReadSchema getCoordinates(@PathVariable("id") Long coordinatesId) {
        return coordinatesService.getCoordinates(coordinatesId);
    }


    @Operation(summary = "Добавить координаты")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "422", description = "Валидационная ошибка", content = @Content(schema = @Schema(implementation = Http422Schema.class))),
    })
    @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CoordinatesReadSchema createCoordinates(@RequestBody @Valid CoordinatesCreateSchema schema) {
        return coordinatesService.createCoordinates(schema);
    }


    @Operation(summary = "Изменить координаты")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Объект не найден", content = @Content(schema = @Schema(implementation = Http404Schema.class))),
            @ApiResponse(responseCode = "422", description = "Валидационная ошибка", content = @Content(schema = @Schema(implementation = Http422Schema.class))),
    })
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_XML_VALUE)
    public CoordinatesReadSchema updateCoordinates(@PathVariable("id") Long coordinatesId, @RequestBody @Valid CoordinatesUpdateSchema schema){
        return coordinatesService.updateCoordinates(coordinatesId, schema);
    }


    @Operation(summary = "Удалить координаты")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Объект не найден", content = @Content(schema = @Schema(implementation = Http404Schema.class))),
    })
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(path = "/{id}") // todo: check type
    private void deleteCoordinates(@PathVariable("id") Long coordinatesId){
        coordinatesService.deleteCoordinates(coordinatesId);
    }
}
