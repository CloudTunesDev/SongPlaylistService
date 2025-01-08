package com.cloudtunes.songplaylistserv.song;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SongConverterTests {

    @Test
    void testToSongDTOWithValidSong() {
        // Arrange
        Song song = new Song();
        song.setId(1L);
        song.setTitle("Test Title");
        song.setArtist("Test Artist");
        song.setExplicit(true);
        song.setDuration(3.5);

        // Act
        SongDTO songDTO = SongConverter.ToSongDTO(song);

        // Assert
        assertNotNull(songDTO);
        assertEquals(1L, songDTO.getId());
        assertEquals("Test Title", songDTO.getTitle());
        assertEquals("Test Artist", songDTO.getArtist());
        assertTrue(songDTO.isExplicit());
        assertEquals(3.5, songDTO.getDuration(), 0.001);
    }

    @Test
    void testToSongDTOWithNullSong() {
        // Act
        SongDTO songDTO = SongConverter.ToSongDTO(null);

        // Assert
        assertNull(songDTO);
    }

    @Test
    void testToEntityWithValidSongDTO() {
        // Arrange
        SongDTO songDTO = SongDTO.builder()
                .title("Test Title")
                .artist("Test Artist")
                .isExplicit(true)
                .duration(3.5)
                .build();

        // Act
        Song song = SongConverter.ToEntity(songDTO);

        // Assert
        assertNotNull(song);
        assertNull(song.getId()); // id should not be set from DTO
        assertEquals("Test Title", song.getTitle());
        assertEquals("Test Artist", song.getArtist());
        assertTrue(song.isExplicit());
        assertEquals(3.5, song.getDuration(), 0.001);
    }

    @Test
    void testToEntityWithNullSongDTO() {
        // Act
        Song song = SongConverter.ToEntity(null);

        // Assert
        assertNull(song);
    }
}

