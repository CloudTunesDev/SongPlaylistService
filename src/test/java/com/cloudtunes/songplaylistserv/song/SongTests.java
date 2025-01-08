package com.cloudtunes.songplaylistserv.song;

import com.cloudtunes.songplaylistserv.album.Album;
import com.cloudtunes.songplaylistserv.playlist.Playlist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SongTests {

    private Song song;

    @BeforeEach
    void setUp() {
        song = new Song();
    }

    @Test
    void testGettersAndSetters() {
        // Test getters and setters using Lombok annotations
        song.setId(1L);
        song.setTitle("Test Title");
        song.setArtist("Test Artist");
        song.setExplicit(true);
        song.setDuration(3.5);

        assertEquals(1L, song.getId());
        assertEquals("Test Title", song.getTitle());
        assertEquals("Test Artist", song.getArtist());
        assertTrue(song.isExplicit());
        assertEquals(3.5, song.getDuration(), 0.001);
    }

    @Test
    void testPlaylistAssociation() {
        // Test association with Playlist
        Playlist playlist1 = new Playlist();
        Playlist playlist2 = new Playlist();
        List<Playlist> playlists = new ArrayList<>();
        playlists.add(playlist1);
        playlists.add(playlist2);

        song.setPlaylist(playlists);

        assertEquals(playlists, song.getPlaylist());
    }

    @Test
    void testAlbumAssociation() {
        // Test association with Album
        Album album = new Album();
        song.setAlbum(album);

        assertEquals(album, song.getAlbum());
    }

    @Test
    void testEqualsAndHashCode() {
        // Test equals and hashCode methods
        Song song1 = new Song();
        Song song2 = new Song();

        assertEquals(song1, song2);
        assertEquals(song1.hashCode(), song2.hashCode());

        song1.setId(1L);
        assertNotEquals(song1, song2);
        assertNotEquals(song1.hashCode(), song2.hashCode());

        song2.setId(1L);
        assertEquals(song1, song2);
        assertEquals(song1.hashCode(), song2.hashCode());
    }

    @Test
    void testConstructorWithArguments() {
        // Test constructor with arguments
        List<Playlist> playlists = new ArrayList<>();
        Playlist playlist = new Playlist();
        playlists.add(playlist);
        Album album = new Album();

        Song songWithArgs = new Song(1L, "Test Title", "Test Artist", true, 3.5, playlists, album);

        assertEquals(1L, songWithArgs.getId());
        assertEquals("Test Title", songWithArgs.getTitle());
        assertEquals("Test Artist", songWithArgs.getArtist());
        assertTrue(songWithArgs.isExplicit());
        assertEquals(3.5, songWithArgs.getDuration(), 0.001);
        assertEquals(playlists, songWithArgs.getPlaylist());
        assertEquals(album, songWithArgs.getAlbum());
    }

}
