package com.musicbands.musicbandsservice.controllers;

import com.musicbands.musicbandsservice.schemas.coordinates.CoordinatesCreateSchema;
import com.musicbands.musicbandsservice.schemas.coordinates.CoordinatesReadSchema;
import com.musicbands.musicbandsservice.schemas.coordinates.CoordinatesUpdateSchema;
import com.musicbands.musicbandsservice.services.CoordinatesService;
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

    @GetMapping("/{id}")
    public CoordinatesReadSchema getCoordinates(@PathVariable("id") Long coordinatesId) {
        return coordinatesService.getCoordinates(coordinatesId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CoordinatesReadSchema createCoordinates(@RequestBody @Valid CoordinatesCreateSchema schema) {
        return coordinatesService.createCoordinates(schema);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_XML_VALUE)
    public CoordinatesReadSchema updateCoordinates(@PathVariable("id") Long coordinatesId, @RequestBody @Valid CoordinatesUpdateSchema schema){
        return coordinatesService.updateCoordinates(coordinatesId, schema);
    }

    @DeleteMapping(path = "/{id}") // todo: check type
    private void deleteCoordinates(@PathVariable("id") Long coordinatesId){
        coordinatesService.deleteCoordinates(coordinatesId);
    }
}
