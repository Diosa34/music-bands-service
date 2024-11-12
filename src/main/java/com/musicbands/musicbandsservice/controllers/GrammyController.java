package com.musicbands.musicbandsservice.controllers;

import com.musicbands.musicbandsservice.schemas.single.SingleBaseSchema;
import com.musicbands.musicbandsservice.schemas.single.SingleReadSchema;
import com.musicbands.musicbandsservice.services.SingleService;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Hidden
@RestController
@RequiredArgsConstructor
@RequestMapping("/grammy")
@Validated
public class GrammyController {
    private final SingleService singleService;

    @PostMapping("/singles/add")
    public SingleReadSchema addSingle(
            @RequestParam Long musicBandId,
            @RequestBody @Valid SingleBaseSchema singleBaseSchema) {
        return singleService.add(musicBandId, singleBaseSchema);
    }
}
