package com.cloudtunes.songplaylistserv.album.unit;

import com.cloudtunes.songplaylistserv.album.Album;
import com.cloudtunes.songplaylistserv.album.AlbumDTO;
import com.cloudtunes.songplaylistserv.album.AlbumRepository;
import com.cloudtunes.songplaylistserv.album.AlbumService;
import com.cloudtunes.songplaylistserv.song.SongRepository;
import com.cloudtunes.songplaylistserv.user.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class AlbumServiceUnitTests {

    @Mock
    private AlbumRepository albumRepository;

    @Mock
    private SongRepository songRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private AlbumService albumService;

    @Test
    void getAlbumById_ValidId_ReturnsAlbumDTO() {
        // Arrange
        Long albumId = 1L;
        Album album = createSampleAlbum();
        when(albumRepository.findById(albumId)).thenReturn(Optional.of(album));

        // Act
        Optional<AlbumDTO> result = albumService.getAlbumById(albumId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(album.getTitle(), result.get().getTitle());
        assertEquals(album.getArtist(), result.get().getArtist());
        assertEquals(album.getYear(), result.get().getYear());
        assertEquals(album.getGenre(), result.get().getGenre());
    }

    @Test
    void getAlbumById_InvalidId_ReturnsEmptyOptional() {
        // Arrange
        Long albumId = 1L;
        when(albumRepository.findById(albumId)).thenReturn(Optional.empty());

        // Act
        Optional<AlbumDTO> result = albumService.getAlbumById(albumId);

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void getAllAlbums_ReturnsListOfAlbumDTOs() {
        // Arrange
        Iterable<Album> albums = createSampleAlbumsIterable();
        when(albumRepository.findAll()).thenReturn(albums);

        // Act
        List<AlbumDTO> result = albumService.getAllAlbums();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void getAllUserAlbums_ReturnsListOfAlbumDTOs() {
        // Arrange
        long userId = 1L;
        Iterable<Album> albums = createSampleAlbumsIterable();
        when(albumRepository.findByUserId(userId)).thenReturn((List<Album>) albums);

        // Act
        List<AlbumDTO> result = albumService.getAllUserAlbums(userId);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void getCountedUserAlbums_ReturnsCount() {
        // Arrange
        long userId = 1L;
        when(albumRepository.countAllAlbumsByUserId(userId)).thenReturn(3L);

        // Act
        Long result = albumService.getCountedUserAlbums(userId);

        // Assert
        assertEquals(3L, result);
    }

    @Test
    void saveAlbum_ValidInput_ReturnsAlbumDTO() {
        // Arrange
        AlbumDTO albumDTO = createSampleAlbumDTO();
        Album album = createSampleAlbum();
        when(albumRepository.save(any())).thenReturn(album);

        // Act
        AlbumDTO result = albumService.saveAlbum(albumDTO);

        // Assert
        assertNotNull(result);
        assertEquals(albumDTO.getTitle(), result.getTitle());
        assertEquals(albumDTO.getArtist(), result.getArtist());
        assertEquals(albumDTO.getYear(), result.getYear());
        assertEquals(albumDTO.getGenre(), result.getGenre());
    }

    @Test
    void updateAlbum_ValidInput_ReturnsUpdatedAlbumDTO() {
        // Arrange
        AlbumDTO albumDTO = createSampleAlbumDTO();
        Album existingAlbum = createSampleAlbum();
        when(albumRepository.findById(any())).thenReturn(Optional.of(existingAlbum));
        when(albumRepository.save(any())).thenReturn(existingAlbum);

        // Act
        Optional<AlbumDTO> result = albumService.updateAlbum(albumDTO);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(albumDTO.getTitle(), result.get().getTitle());
        assertEquals(albumDTO.getArtist(), result.get().getArtist());
        assertEquals(albumDTO.getYear(), result.get().getYear());
        assertEquals(albumDTO.getGenre(), result.get().getGenre());
    }

    @Test
    void updateAlbum_InvalidAlbumId_ReturnsEmptyOptional() {
        // Arrange
        AlbumDTO albumDTO = createSampleAlbumDTO();
        when(albumRepository.findById(any())).thenReturn(Optional.empty());

        // Act
        Optional<AlbumDTO> result = albumService.updateAlbum(albumDTO);

        // Assert
        assertTrue(result.isEmpty());
        verify(albumRepository, never()).save(any());
    }

//    @Disabled
//    @Test
//    void deleteAlbum_ValidId_DeletesAlbum() {
//        // Arrange
//        Long albumId = 1L;
//
//        // Act
//        albumService.deleteAlbum(albumId);
//
//        // Assert
//        verify(songRepository, times(1)).findByAlbumId(albumId);
//        verify(songRepository, times(2)).deleteById(any());
//        verify(albumRepository, times(1)).deleteById(albumId);
//    }

    // Helper methods for creating sample data
    private AlbumDTO createSampleAlbumDTO() {
        AlbumDTO albumDTO = new AlbumDTO();
        albumDTO.setId(1L);
        albumDTO.setTitle("Test Album");
        albumDTO.setArtist("Test Artist");
        albumDTO.setYear(2022);
        albumDTO.setGenre("Rock");

        return albumDTO;
    }

    private Album createSampleAlbum() {
        Album album = new Album();
        album.setId(1L);
        album.setTitle("Test Album");
        album.setArtist("Test Artist");
        album.setYear(2022);
        album.setGenre("Rock");

        return album;
    }

    private Iterable<Album> createSampleAlbumsIterable() {
        return List.of(createSampleAlbum(), createSampleAlbum());
    }
}
