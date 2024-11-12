package com.musicbands.musicbandsservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reward {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "musicBand_id", nullable = false)
    private MusicBand musicBand;

    @Column(nullable = false)
    private MusicGenre genre;
}
