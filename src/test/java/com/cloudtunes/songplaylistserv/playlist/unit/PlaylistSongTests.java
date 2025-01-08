package com.cloudtunes.songplaylistserv.playlist.unit;

import com.cloudtunes.songplaylistserv.playlist.Playlist;
import com.cloudtunes.songplaylistserv.playlist.playlistsongcombo.PlaylistSong;
import com.cloudtunes.songplaylistserv.song.Song;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PlaylistSongTests {

    @Test
    public void testNoArgsConstructor() {
        PlaylistSong playlistSong = new PlaylistSong();
        assertNotNull(playlistSong);
    }

    @Test
    public void testAllArgsConstructor() {
        Playlist playlist = new Playlist();
        Song song = new Song();
        PlaylistSong playlistSong = new PlaylistSong(1L, playlist, song);
        assertNotNull(playlistSong);
        assertEquals(1L, playlistSong.getId());
        assertEquals(playlist, playlistSong.getPlaylist());
        assertEquals(song, playlistSong.getSong());
    }

    @Test
    public void testLombokDataAnnotation() {
        PlaylistSong playlistSong1 = new PlaylistSong();
        PlaylistSong playlistSong2 = new PlaylistSong();

        assertEquals(playlistSong1, playlistSong2);
        assertEquals(playlistSong1.hashCode(), playlistSong2.hashCode());
        assertNotNull(playlistSong1.toString());
    }

    @Test
    public void testLombokSuperBuilderAnnotation() {
        PlaylistSong playlistSong = PlaylistSong.builder().id(1L).build();

        assertNotNull(playlistSong);
        assertEquals(1L, playlistSong.getId());
    }

    @Test
    public void testLombokNoArgsConstructor() {
        PlaylistSong playlistSong = new PlaylistSong();

        assertNotNull(playlistSong);
    }

    @Test
    public void testLombokAllArgsConstructor() {
        Playlist playlist = new Playlist();
        Song song = new Song();
        PlaylistSong playlistSong = PlaylistSong.builder().id(1L).playlist(playlist).song(song).build();

        assertNotNull(playlistSong);
        assertEquals(1L, playlistSong.getId());
        assertEquals(playlist, playlistSong.getPlaylist());
        assertEquals(song, playlistSong.getSong());
    }
}
