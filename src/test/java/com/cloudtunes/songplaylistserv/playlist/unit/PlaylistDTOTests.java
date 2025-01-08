package com.cloudtunes.songplaylistserv.playlist.unit;

import com.cloudtunes.songplaylistserv.song.SongDTO;
import org.junit.jupiter.api.Test;
import com.cloudtunes.songplaylistserv.playlist.PlaylistDTO;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PlaylistDTOTests {
//    @Test
//    public void testNoArgsConstructor() {
//        PlaylistDTO playlistDTO = new PlaylistDTO();
//        assertNotNull(playlistDTO);
//    }
//
//    @Test
//    public void testAllArgsConstructor() {
//        SongDTO songDTO = createSampleSongDTO();
//        PlaylistDTO playlistDTO = new PlaylistDTO(1L, "Test Playlist", createSampleUser(), Collections.singletonList(songDTO));
//        assertEquals(1L, playlistDTO.getId());
//        assertEquals("Test Playlist", playlistDTO.getTitle());
//        assertEquals(createSampleUser(), playlistDTO.getUser());
//        assertEquals(Collections.singletonList(songDTO), playlistDTO.getSongList());
//    }
//
//    @Test
//    public void testGettersAndSetters() {
//        PlaylistDTO playlistDTO = new PlaylistDTO();
//        playlistDTO.setId(1L);
//        playlistDTO.setTitle("Test Playlist");
//        playlistDTO.setUser(createSampleUser());
//        SongDTO songDTO = createSampleSongDTO();
//        playlistDTO.setSongList(Collections.singletonList(songDTO));
//
//        assertEquals(1L, playlistDTO.getId());
//        assertEquals("Test Playlist", playlistDTO.getTitle());
//        assertEquals(createSampleUser(), playlistDTO.getUser());
//        assertEquals(Collections.singletonList(songDTO), playlistDTO.getSongList());
//    }
//
//    @Test
//    public void testEqualsAndHashCode() {
//        SongDTO songDTO = createSampleSongDTO();
//        PlaylistDTO playlistDTO1 = new PlaylistDTO(1L, "Test Playlist", createSampleUser(), Collections.singletonList(songDTO));
//        PlaylistDTO playlistDTO2 = new PlaylistDTO(1L, "Test Playlist", createSampleUser(), Collections.singletonList(songDTO));
//        PlaylistDTO playlistDTO3 = new PlaylistDTO(2L, "Another Playlist", createSampleUser(), Collections.singletonList(songDTO));
//
//        assertEquals(playlistDTO1, playlistDTO2);
//        assertNotEquals(playlistDTO1, playlistDTO3);
//
//        assertEquals(playlistDTO1.hashCode(), playlistDTO2.hashCode());
//        assertNotEquals(playlistDTO1.hashCode(), playlistDTO3.hashCode());
//    }
//
//    // Add tests for other Lombok-generated methods (toString, builder) if necessary
//
//    private SongDTO createSampleSongDTO() {
//        return SongDTO.builder()
//                .Id(1L)
//                .title("Test Song")
//                .artist("Test Artist")
//                .isExplicit(true)
//                .duration(3.5)
//                .build();
//    }
//
//    private User createSampleUser() {
//        // Implement the creation of a sample user as needed
//        return new User();
//    }
}
