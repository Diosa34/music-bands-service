package com.musicbands.musicbandsservice.mappers;

import com.musicbands.musicbandsservice.exceptions.NotFoundException;
import com.musicbands.musicbandsservice.models.Coordinates;
import com.musicbands.musicbandsservice.models.Label;
import com.musicbands.musicbandsservice.models.MusicBand;
import com.musicbands.musicbandsservice.repositories.CoordinatesRepository;
import com.musicbands.musicbandsservice.repositories.LabelRepository;
import com.musicbands.musicbandsservice.repositories.MusicBandRepository;
import com.musicbands.musicbandsservice.schemas.musicBand.MusicBandReadSchema;
import com.musicbands.musicbandsservice.schemas.musicBand.MusicBandXMLSchema;
import jakarta.validation.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MusicBandMapper {
    private final CoordinatesMapper coordinatesMapper;
    private final LabelMapper labelMapper;
    private final MusicBandRepository musicBandRepository;
    private final CoordinatesRepository coordinatesRepository;
    private final LabelRepository labelRepository;

    public MusicBandReadSchema mapEntityToMusicBandRead(MusicBand entity) {
        MusicBandReadSchema schema = new MusicBandReadSchema();
        schema.setId(entity.getId());
        schema.setName(entity.getName());
        schema.setCoordinates(
                coordinatesMapper.mapEntityToCoordinatesRead(entity.getCoordinates())
        );
        schema.setNumberOfParticipants(entity.getNumberOfParticipants());
        schema.setDescription(entity.getDescription());
        schema.setGenre(entity.getGenre());
        schema.setCreationDate(entity.getCreationDate());
        schema.setLabel(
                labelMapper.mapEntityToLabelRead(entity.getLabel()));
        return schema;
    }

    public MusicBand mapMusicBandXMLToEntity(MusicBandXMLSchema xmlSchema) throws NotFoundException {
        MusicBand entity = new MusicBand();
        return mapSchemaFieldsToEntity(entity, xmlSchema);
    }

    public MusicBand mapMusicBandUpdateToEntity(Long id, MusicBandXMLSchema xmlSchema) {
        MusicBand entity = musicBandRepository.getReferenceById(id);
        return mapSchemaFieldsToEntity(entity, xmlSchema);
    }

    public MusicBand mapSchemaFieldsToEntity(MusicBand entity, MusicBandXMLSchema xmlSchema) {
        entity.setName(xmlSchema.getName());
        entity.setCoordinates(coordCheck(xmlSchema.getCoordinatesID(), xmlSchema.getX(), xmlSchema.getY()));
        entity.setDescription(xmlSchema.getDescription());
        entity.setGenre(xmlSchema.getGenre());
        entity.setLabel(labelCheck(xmlSchema.getLabelId(), xmlSchema.getLabelName()));
        return entity;
    }

    private Coordinates coordCheck(Long id, Double x, Long y) {
        Coordinates coord;

        if (id != null) {
            coord = coordinatesRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException(id, "Coordinates не найдены"));
        } else if (x != null && y != null) {
            coord = new Coordinates();
            coord.setX(x);
            coord.setY(y);
            coordinatesRepository.save(coord);
        } else {
            throw new ValidationException("Некорректные данные о Coordinates: необходимо указать координаты или ID.");
        }
        return coord;
    }

    private Label labelCheck(Long labelID, String labelName) {
        Label label;

        if (labelID != null) {
            label = labelRepository.findById(labelID)
                    .orElseThrow(() -> new NotFoundException(labelID, "Label не найдена"));
        } else if (labelName != null) {
            label = new Label();
            label.setName(labelName);
            labelRepository.save(label);
        } else {
            throw new ValidationException("Некорректные данные о Label: необходимо указать имя или ID.");
        }
        return label;
    }
}
