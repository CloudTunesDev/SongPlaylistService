package com.cloudtunes.songplaylistserv.album.unit;

import org.junit.jupiter.api.Test;
import com.cloudtunes.songplaylistserv.album.Album;
import com.cloudtunes.songplaylistserv.album.AlbumConverter;
import com.cloudtunes.songplaylistserv.album.AlbumDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class AlbumConverterTests {
    @Test
    void albumConverter_ShouldConvertAlbumToDTO() {
        // Arrange
        Album album = new Album();
        album.setTitle("Test Album");
        album.setArtist("Test Artist");
        album.setYear(2022);
        album.setGenre("Test Genre");

        // Act
        AlbumDTO albumDTO = AlbumConverter.ToAlbumDTO(album);

        // Assert
        assertEquals(album.getTitle(), albumDTO.getTitle());
        assertEquals(album.getArtist(), albumDTO.getArtist());
        assertEquals(album.getYear(), albumDTO.getYear());
        assertEquals(album.getGenre(), albumDTO.getGenre());
    }

    @Test
    void albumConverter_WithNullAlbum_ShouldReturnNull() {
        // Act
        AlbumDTO albumDTO = AlbumConverter.ToAlbumDTO(null);

        // Assert
        assertNull(albumDTO);
    }
}

