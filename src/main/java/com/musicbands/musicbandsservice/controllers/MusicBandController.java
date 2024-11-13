package com.musicbands.musicbandsservice.controllers;

import com.musicbands.musicbandsservice.exceptions.schemas.BadRequestSchema;
import com.musicbands.musicbandsservice.exceptions.schemas.InternalServerErrorSchema;
import com.musicbands.musicbandsservice.exceptions.schemas.ObjectNotFoundSchema;
import com.musicbands.musicbandsservice.exceptions.schemas.ValidationExceptionSchema;
import com.musicbands.musicbandsservice.schemas.GroupSchema;
import com.musicbands.musicbandsservice.schemas.StatisticSchema;
import com.musicbands.musicbandsservice.schemas.musicBand.*;
import com.musicbands.musicbandsservice.services.MusicBandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful response"),
            @ApiResponse(responseCode = "400", description = "Invalid query param", content = @Content(schema = @Schema(implementation = BadRequestSchema.class))),
            @ApiResponse(responseCode = "422", description = "Validation error", content = @Content(schema = @Schema(implementation = ValidationExceptionSchema.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = InternalServerErrorSchema.class)))
    })
    @GetMapping
    public Set<MusicBandReadSchema> getMusicBandList() {
        return musicBandService.getAll();
    }

    @Operation(
            summary = "Создать музыкальную группу",
            description = "Для создания музыкальной группы с имеющимися в БД coordinates и label," +
                    " достаточно указать их id." +
                    " Для создания музыкальной группы с новыми, ещё не занесёнными в БД coordinates и label," +
                    " необходимо указать значения x, y, labelName."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns the music band that was just added"),
            @ApiResponse(responseCode = "400", description = "Invalid query param", content = @Content(schema = @Schema(implementation = BadRequestSchema.class))),
            @ApiResponse(responseCode = "422", description = "Validation error", content = @Content(schema = @Schema(implementation = ValidationExceptionSchema.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = InternalServerErrorSchema.class)))
    })
    @PostMapping
    public MusicBandReadSchema addMusicBand(@RequestBody @Valid MusicBandXMLSchema schema) {
        return musicBandService.add(schema);
    }

    @Operation(
            summary = "Получить музыкальную группу по ID.",
            description = "Возвращает информацию о музыкальной группе"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns music band by id"),
            @ApiResponse(responseCode = "400", description = "Invalid query param", content = @Content(schema = @Schema(implementation = BadRequestSchema.class))),
            @ApiResponse(responseCode = "422", description = "Validation error", content = @Content(schema = @Schema(implementation = ValidationExceptionSchema.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = InternalServerErrorSchema.class)))
    })
    @GetMapping("/{id}")
    public MusicBandReadSchema getMusicBand(@PathVariable Long id) {
        return musicBandService.getById(id);
    }

    @Operation(
            summary = "Обновить музыкальную группу",
            description = "Обновляет информацию о музыкальной группе"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns an updated music band"),
            @ApiResponse(responseCode = "400", description = "Invalid query param", content = @Content(schema = @Schema(implementation = BadRequestSchema.class))),
            @ApiResponse(responseCode = "404", description = "Object not found", content = @Content(schema = @Schema(implementation = ObjectNotFoundSchema.class))),
            @ApiResponse(responseCode = "422", description = "Validation error", content = @Content(schema = @Schema(implementation = ValidationExceptionSchema.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = InternalServerErrorSchema.class)))
    })
    @PutMapping("/{id}")
    public MusicBandReadSchema updateMusicBand(@PathVariable Long id, @RequestBody @Valid MusicBandXMLSchema schema) {
        return musicBandService.update(id, schema);
    }

    @Operation(summary = "Удаляет музыкальную группу")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "The music band was successfully deleted"),
            @ApiResponse(responseCode = "400", description = "Invalid query param", content = @Content(schema = @Schema(implementation = BadRequestSchema.class))),
            @ApiResponse(responseCode = "404", description = "Object not found", content = @Content(schema = @Schema(implementation = ObjectNotFoundSchema.class))),
            @ApiResponse(responseCode = "422", description = "Validation error", content = @Content(schema = @Schema(implementation = ValidationExceptionSchema.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = InternalServerErrorSchema.class)))
    })
    @DeleteMapping("/{id}")
    public void deleteMusicBand(@PathVariable Long id) {
        musicBandService.delete(id);
    }

    @Operation(
            summary = "Получить среднее количество участников для всех музыкальных групп"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns the average value of the number of participants"),
            @ApiResponse(responseCode = "400", description = "Invalid query param", content = @Content(schema = @Schema(implementation = BadRequestSchema.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = InternalServerErrorSchema.class)))
    })
    @GetMapping("/statistic")
    public StatisticSchema getStatistic() {
        return musicBandService.getStatistic();
    }

    @Operation(
            summary = "Получите количество повторений для каждого значения имени"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns list of numbers of each Name value"),
            @ApiResponse(responseCode = "400", description = "Invalid query param", content = @Content(schema = @Schema(implementation = BadRequestSchema.class))),
            @ApiResponse(responseCode = "422", description = "Validation error", content = @Content(schema = @Schema(implementation = ValidationExceptionSchema.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = InternalServerErrorSchema.class)))
    })
    @GetMapping("/groups")
    public List<GroupSchema> getGroups() {
        return musicBandService.getGroups();
    }
}
