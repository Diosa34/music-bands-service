package com.musicbands.musicbandsservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MusicBand {
    @Id
    @GeneratedValue
    @Positive
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "coordinates_id", nullable = false)
    private Coordinates coordinates;

    @Convert(converter = ZonedDateTimeAttributeConverter.class)
    @Column(nullable = false)
    private ZonedDateTime creationDate = ZonedDateTime.now();

    @Positive
    @Column
    private Long numberOfParticipants;

    @Column
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MusicGenre genre;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "label_id")
    private Label label;

    @OneToMany(mappedBy = "musicBand")
    private List<Single> singls = new ArrayList<>();
}
