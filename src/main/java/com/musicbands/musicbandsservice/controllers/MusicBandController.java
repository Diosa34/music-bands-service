package com.musicbands.musicbandsservice.controllers;

import com.musicbands.musicbandsservice.schemas.GroupSchema;
import com.musicbands.musicbandsservice.schemas.StatisticSchema;
import com.musicbands.musicbandsservice.schemas.exceptions.Http404Schema;
import com.musicbands.musicbandsservice.schemas.exceptions.Http422Schema;
import com.musicbands.musicbandsservice.schemas.lists.ListWithPaginatorSchema;
import com.musicbands.musicbandsservice.schemas.musicBand.MusicBandCreateSchema;
import com.musicbands.musicbandsservice.schemas.musicBand.MusicBandFilterSchema;
import com.musicbands.musicbandsservice.schemas.musicBand.MusicBandReadSchema;
import com.musicbands.musicbandsservice.schemas.musicBand.MusicBandUpdateSchema;
import com.musicbands.musicbandsservice.services.MusicBandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Музыкальные группы (Music Bands)")
@RestController
@AllArgsConstructor
@RequestMapping(path = "/music-bands", produces = MediaType.APPLICATION_XML_VALUE)
@Validated
public class MusicBandController {
    private final MusicBandService musicBandService;

    @Operation(summary = "Список музыкальных групп")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ListWithPaginatorSchema<MusicBandReadSchema> filterMusicBand(@ParameterObject @Valid MusicBandFilterSchema filterSchema) {
        return musicBandService.filterMusicBand(filterSchema);
    }

    @Operation(
            summary = "Создать музыкальную группу",
            description = "Для создания музыкальной группы с имеющимися в БД coordinates и label," +
                    " достаточно указать их id." +
                    " Для создания музыкальной группы с новыми, ещё не занесёнными в БД coordinates и label," +
                    " необходимо указать значения x, y, labelName."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Объект не найден", content = @Content(schema = @Schema(implementation = Http404Schema.class))),
            @ApiResponse(responseCode = "422", description = "Валидационная ошибка", content = @Content(schema = @Schema(implementation = Http422Schema.class))),
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE)
    public MusicBandReadSchema addMusicBand(@RequestBody @Valid MusicBandCreateSchema schema) {
        return musicBandService.createMusicBand(schema);
    }

    @Operation(
            summary = "Получить музыкальную группу",
            description = "Возвращает информацию о музыкальной группе по ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Объект не найден", content = @Content(schema = @Schema(implementation = Http404Schema.class))),
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public MusicBandReadSchema getMusicBand(@PathVariable Long id) {
        return musicBandService.getMusicBand(id);
    }

    @Operation(
            summary = "Обновить музыкальную группу",
            description = "Обновляет информацию о музыкальной группе"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Объект не найден", content = @Content(schema = @Schema(implementation = Http404Schema.class))),
            @ApiResponse(responseCode = "422", description = "Валидационная ошибка", content = @Content(schema = @Schema(implementation = Http422Schema.class))),
    })
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_XML_VALUE)
    public MusicBandReadSchema updateMusicBand(@PathVariable Long id, @RequestBody @Valid MusicBandUpdateSchema schema) {
        return musicBandService.updateMusicBand(id, schema);
    }

    @Operation(summary = "Удаляет музыкальную группу")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Объект не найден", content = @Content(schema = @Schema(implementation = Http404Schema.class)))
    })
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void deleteMusicBand(@PathVariable Long id) {
        musicBandService.deleteMusicBand(id);
    }

    @Operation(summary = "Получить среднее количество участников для всех музыкальных групп")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/statistic")
    public StatisticSchema getStatistic() {
        return musicBandService.getStatistic();
    }

    @Operation(summary = "Получите количество повторений для каждого значения имени")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/groups")
    public List<GroupSchema> getGroups() {
        return musicBandService.getGroups();
    }
}
