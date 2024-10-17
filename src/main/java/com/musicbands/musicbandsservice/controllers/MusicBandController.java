package com.musicbands.musicbandsservice.controllers;

import com.musicbands.musicbandsservice.schemas.GroupSchema;
import com.musicbands.musicbandsservice.schemas.StatisticSchema;
import com.musicbands.musicbandsservice.schemas.musicBand.*;
import com.musicbands.musicbandsservice.services.MusicBandService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @Operation(
            summary = "Получить музыкальную группу по ID.",
            description = "Возвращает информацию о музыкальной группе"
    )
    @GetMapping("/{id}")
    public MusicBandReadSchema getMusicBand(@PathVariable Long id) {
        return musicBandService.getById(id);
    }

    @Operation(
            summary = "Обновить музыкальную группу",
            description = "Обновляет информацию о музыкальной группе"
    )
    @PutMapping("/{id}")
    public MusicBandReadSchema updateMusicBand(@PathVariable Long id, @RequestBody @Valid MusicBandXMLSchema schema) {
        return musicBandService.update(id, schema);
    }

    @Operation(summary = "Удаляет музыкальную группу")
    @DeleteMapping("/{id}")
    public void deleteMusicBand(@PathVariable Long id) {
        musicBandService.delete(id);
    }

    @Operation(
            summary = "Получить среднее количество участников для всех музыкальных групп"
    )
    @GetMapping("/statistic")
    public StatisticSchema getStatistic() {
        return musicBandService.getStatistic();
    }

    @Operation(
            summary = "Получите количество повторений для каждого значения имени"
    )
    @GetMapping("/groups")
    public List<GroupSchema> getGroups() {
        return musicBandService.getGroups();
    }
}
