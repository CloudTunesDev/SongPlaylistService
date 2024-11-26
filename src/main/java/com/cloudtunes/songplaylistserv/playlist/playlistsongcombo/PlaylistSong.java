package com.cloudtunes.songplaylistserv.playlist.playlistsongcombo;

import com.cloudtunes.songplaylistserv.playlist.Playlist;
import com.cloudtunes.songplaylistserv.song.Song;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity(name = "song_playlist")
public class PlaylistSong {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Playlist playlist;

    @ManyToOne(fetch = FetchType.EAGER)
    private Song song;

}
