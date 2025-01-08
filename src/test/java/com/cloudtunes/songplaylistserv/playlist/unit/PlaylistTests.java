package com.cloudtunes.songplaylistserv.playlist.unit;

import com.cloudtunes.songplaylistserv.playlist.Playlist;
import com.cloudtunes.songplaylistserv.user.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlaylistTests {

    @Test
    public void testNoArgsConstructor() {
        Playlist playlist = new Playlist();
        assertNotNull(playlist);
    }

    @Test
    public void testAllArgsConstructor() {
        User user = new User();
        Playlist playlist = new Playlist(1L, "Test Playlist", user);
        assertEquals(1L, playlist.getId());
        assertEquals("Test Playlist", playlist.getTitle());
        assertEquals(user, playlist.getUser());
    }

    @Test
    public void testGettersAndSetters() {
        Playlist playlist = new Playlist();
        playlist.setId(1L);
        playlist.setTitle("Test Playlist");
        User user = new User();
        playlist.setUser(user);

        assertEquals(1L, playlist.getId());
        assertEquals("Test Playlist", playlist.getTitle());
        assertEquals(user, playlist.getUser());
    }

    @Test
    public void testEqualsAndHashCode() {
        Playlist playlist1 = new Playlist(1L, "Test Playlist", new User());
        Playlist playlist2 = new Playlist(1L, "Test Playlist", new User());
        Playlist playlist3 = new Playlist(2L, "Another Playlist", new User());

        assertEquals(playlist1, playlist2);
        assertNotEquals(playlist1, playlist3);

        assertEquals(playlist1.hashCode(), playlist2.hashCode());
        assertNotEquals(playlist1.hashCode(), playlist3.hashCode());
    }
}

