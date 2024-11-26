package com.cloudtunes.songplaylistserv.playlist;

import com.cloudtunes.songplaylistserv.song.SongDTO;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
public class PlaylistDTO {
    private long Id;

    @Valid
    private String title;

    //private User user;
    private List<SongDTO> songList;
}
