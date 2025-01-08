package com.cloudtunes.songplaylistserv.album;

import com.cloudtunes.songplaylistserv.song.SongDTO;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import com.cloudtunes.songplaylistserv.user.User;

import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
public class AlbumDTO {
    private Long id;

    @Valid
    private String title;
    @Valid
    private String artist;
    @Valid
    private int year;
    @Valid
    private String genre;
    private User user;
    private List<SongDTO> songs;
}
