package com.musicbands.musicbandsservice.controllers;

import com.musicbands.musicbandsservice.schemas.musicBand.*;
import com.musicbands.musicbandsservice.services.MusicBandService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @Operation(
            summary = "Создать музыкальную группу",
            description = "Для создания музыкальной группы с имеющимися в БД coordinates и label," +
                    " достаточно указать их id." +
                    " Для создания музыкальной группы с новыми, ещё не занесёнными в БД coordinates и label," +
                    " необходимо указать значения x, y, labelName."
    )
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public MusicBandReadSchema addMusicBand(@RequestBody @Valid MusicBandXMLSchema schema) {
        return musicBandService.add(schema);
    }
}
