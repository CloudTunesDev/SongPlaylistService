package com.cloudtunes.songplaylistserv.album.unit;

import com.cloudtunes.songplaylistserv.album.AlbumDTO;
import com.cloudtunes.songplaylistserv.song.SongDTO;
import com.cloudtunes.songplaylistserv.user.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class AlbumDTOTests {

    @Test
    void testNoArgsConstructor() {
        AlbumDTO albumDTO = new AlbumDTO();
        assertNull(albumDTO.getId());
        assertNull(albumDTO.getTitle());
        assertNull(albumDTO.getArtist());
        assertEquals(0, albumDTO.getYear());
        assertNull(albumDTO.getGenre());
        assertNull(albumDTO.getUser());
        assertNull(albumDTO.getSongs());
    }

    @Test
    void testAllArgsConstructor() {
        User user = new User();
        SongDTO song = new SongDTO(1L, "Song Title", "Artist", true, 3.5);
        List<SongDTO> songs = List.of(song);

        AlbumDTO albumDTO = new AlbumDTO(1L, "Album Title", "Artist", 2022, "Rock", user, songs);

        assertEquals(1L, albumDTO.getId());
        assertEquals("Album Title", albumDTO.getTitle());
        assertEquals("Artist", albumDTO.getArtist());
        assertEquals(2022, albumDTO.getYear());
        assertEquals("Rock", albumDTO.getGenre());
        assertEquals(user, albumDTO.getUser());
        assertEquals(songs, albumDTO.getSongs());
    }

    @Test
    void testGettersAndSetters() {
        AlbumDTO albumDTO = new AlbumDTO();

        albumDTO.setId(1L);
        albumDTO.setTitle("Album Title");
        albumDTO.setArtist("Artist");
        albumDTO.setYear(2022);
        albumDTO.setGenre("Rock");
        User user = new User();
        albumDTO.setUser(user);
        SongDTO song = new SongDTO(1L, "Song Title", "Artist", true, 3.5);
        List<SongDTO> songs = List.of(song);
        albumDTO.setSongs(songs);

        assertEquals(1L, albumDTO.getId());
        assertEquals("Album Title", albumDTO.getTitle());
        assertEquals("Artist", albumDTO.getArtist());
        assertEquals(2022, albumDTO.getYear());
        assertEquals("Rock", albumDTO.getGenre());
        assertEquals(user, albumDTO.getUser());
        assertEquals(songs, albumDTO.getSongs());
    }

    @Test
    void testDataAnnotation() {
        AlbumDTO albumDTO1 = new AlbumDTO();
        AlbumDTO albumDTO2 = new AlbumDTO();

        // @Data generates equals, hashCode, and toString methods
        assertEquals(albumDTO1, albumDTO2);
        assertEquals(albumDTO1.hashCode(), albumDTO2.hashCode());
        assertEquals(albumDTO1.toString(), albumDTO2.toString());
    }

    @Test
    void testSuperBuilderAnnotation() {
        // @SuperBuilder generates a builder for the class
        User user = new User();
        SongDTO song = new SongDTO(1L, "Song Title", "Artist", true, 3.5);
        List<SongDTO> songs = List.of(song);

        AlbumDTO albumDTO = AlbumDTO.builder()
                .id(1L)
                .title("Album Title")
                .artist("Artist")
                .year(2022)
                .genre("Rock")
                .user(user)
                .songs(songs)
                .build();

        assertEquals(1L, albumDTO.getId());
        assertEquals("Album Title", albumDTO.getTitle());
        assertEquals("Artist", albumDTO.getArtist());
        assertEquals(2022, albumDTO.getYear());
        assertEquals("Rock", albumDTO.getGenre());
        assertEquals(user, albumDTO.getUser());
        assertEquals(songs, albumDTO.getSongs());
    }

    @Test
    void testAllArgsConstructorAccessLevel() {
        // @AllArgsConstructor(access = AccessLevel.PUBLIC) generates a public all-args constructor
        User user = new User();
        SongDTO song = new SongDTO(1L, "Song Title", "Artist", true, 3.5);
        List<SongDTO> songs = List.of(song);

        AlbumDTO albumDTO = new AlbumDTO(1L, "Album Title", "Artist", 2022, "Rock", user, songs);

        assertEquals(1L, albumDTO.getId());
        assertEquals("Album Title", albumDTO.getTitle());
        assertEquals("Artist", albumDTO.getArtist());
        assertEquals(2022, albumDTO.getYear());
        assertEquals("Rock", albumDTO.getGenre());
        assertEquals(user, albumDTO.getUser());
        assertEquals(songs, albumDTO.getSongs());
    }

}
