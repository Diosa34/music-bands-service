package com.musicbands.musicbandsservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MusicBand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Convert(converter = ZonedDateTimeAttributeConverter.class)
    @Column(nullable = false)
    private ZonedDateTime creationDate = ZonedDateTime.now();

    @Column
    private Long numberOfParticipants;

    @Column
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MusicGenre genre;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "coordinates_id", nullable = false)
    private Coordinates coordinates;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "label_id", nullable = false)
    private Label label;
}
