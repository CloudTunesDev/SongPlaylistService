package com.cloudtunes.songplaylistserv.song;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SongDTOTests {

    @Test
    void testConstructorAndGetters() {
        SongDTO songDTO = SongDTO.builder()
                .Id(1L)
                .title("Song Title")
                .artist("Artist Name")
                .isExplicit(true)
                .duration(3.5)
                .build();

        assertEquals(1L, songDTO.getId());
        assertEquals("Song Title", songDTO.getTitle());
        assertEquals("Artist Name", songDTO.getArtist());
        assertTrue(songDTO.isExplicit());
        assertEquals(3.5, songDTO.getDuration(), 0.01); // Allow a small delta for double comparison
    }

    @Test
    void testEqualsAndHashCode() {
        SongDTO songDTO1 = SongDTO.builder()
                .Id(1L)
                .title("Song Title")
                .artist("Artist Name")
                .isExplicit(true)
                .duration(3.5)
                .build();

        SongDTO songDTO2 = SongDTO.builder()
                .Id(1L)
                .title("Song Title")
                .artist("Artist Name")
                .isExplicit(true)
                .duration(3.5)
                .build();

        SongDTO songDTO3 = SongDTO.builder()
                .Id(2L)
                .title("Different Title")
                .artist("Different Artist")
                .isExplicit(false)
                .duration(2.5)
                .build();

        assertEquals(songDTO1, songDTO2);
        assertNotEquals(songDTO1, songDTO3);
        assertEquals(songDTO1.hashCode(), songDTO2.hashCode());
        assertNotEquals(songDTO1.hashCode(), songDTO3.hashCode());
    }

}
