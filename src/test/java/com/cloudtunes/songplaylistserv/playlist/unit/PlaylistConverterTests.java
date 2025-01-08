package com.cloudtunes.songplaylistserv.playlist.unit;

import com.cloudtunes.songplaylistserv.playlist.Playlist;
import com.cloudtunes.songplaylistserv.playlist.PlaylistConverter;
import com.cloudtunes.songplaylistserv.playlist.PlaylistDTO;
import com.cloudtunes.songplaylistserv.user.User;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PlaylistConverterTests {
    @Test
    void playlistConverter_ShouldConvertPlaylistToDTO() {
        // Arrange
        Playlist playlist = new Playlist();
        playlist.setId(1L);
        playlist.setTitle("Test Playlist");
        playlist.setUser(new User());


        // Act
        PlaylistDTO playlistDTO = PlaylistConverter.ToPlaylistDTO(playlist);

        // Assert
        assertEquals(playlist.getId(), playlistDTO.getId());
        assertEquals(playlist.getTitle(), playlistDTO.getTitle());
    }

    @Test
    void playlistConverter_WithNullPlaylist_ShouldReturnNull() {
        // Act
        PlaylistDTO playlistDTO = PlaylistConverter.ToPlaylistDTO(null);

        // Assert
        assertNull(playlistDTO);
    }
}
