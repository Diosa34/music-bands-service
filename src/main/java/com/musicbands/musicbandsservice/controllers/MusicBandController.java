package com.musicbands.musicbandsservice.controllers;

import com.musicbands.musicbandsservice.schemas.musicBand.MusicBandReadSchema;
import com.musicbands.musicbandsservice.services.MusicBandService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/music-bands")
@Validated
public class MusicBandController {
    private final MusicBandService musicBandService;

    @Operation(summary = "Список музыкальных групп")
    @GetMapping
    public Set<MusicBandReadSchema> getUserList() {
        return musicBandService.getAll();
    }
}
