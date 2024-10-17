package com.musicbands.musicbandsservice.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Coordinates {
    @Id
    @GeneratedValue
    private Long id;

    @Min(-530)
    @Column(nullable = false)
    private double x;

    @Column(nullable = false)
    private Long y;

    @OneToMany(mappedBy = "coordinates")
    private List<MusicBand> musicBands = new ArrayList<>();
}
