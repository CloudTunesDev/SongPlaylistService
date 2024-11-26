package com.cloudtunes.songplaylistserv.song;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class SongDTO {
    private Long Id;
    @Valid
    private String title;
    @Valid
    private String artist;
    @Valid
    private boolean isExplicit;
    @Valid
    private double duration;
}