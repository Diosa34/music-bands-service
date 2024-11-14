package com.musicbands.musicbandsservice.controllers;

import com.musicbands.musicbandsservice.schemas.label.LabelCreateSchema;
import com.musicbands.musicbandsservice.schemas.label.LabelReadSchema;
import com.musicbands.musicbandsservice.schemas.label.LabelUpdateSchema;
import com.musicbands.musicbandsservice.services.LabelService;
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

    @GetMapping("/{id}")
    public LabelReadSchema getLabel(@PathVariable("id") Long labelId) {
        return labelService.getLabel(labelId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "", consumes = MediaType.APPLICATION_XML_VALUE)
    public LabelReadSchema createLabel(@RequestBody @Valid LabelCreateSchema schemaf) {
        return labelService.createLabel(schemaf);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_XML_VALUE)
    public LabelReadSchema updateLabel(@PathVariable("id") Long labelId, @RequestBody @Valid LabelUpdateSchema schema){
        return labelService.updateLabel(labelId, schema);
    }


    @DeleteMapping(path = "/{id}") // todo: check type
    private void deleteLabel(@PathVariable("id") Long labelId){
        labelService.deleteLabel(labelId);
    }
}
