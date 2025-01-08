package com.cloudtunes.songplaylistserv.song;

import com.cloudtunes.songplaylistserv.album.Album;
import com.cloudtunes.songplaylistserv.album.AlbumDTO;
import com.cloudtunes.songplaylistserv.album.AlbumRepository;
import com.cloudtunes.songplaylistserv.user.User;
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
class SongServiceTests {

    @Mock
    private SongRepository songRepository;

    @Mock
    private AlbumRepository albumRepository;

    @InjectMocks
    private SongService songService;

    @Test
    void saveSong_ValidInput_ReturnsAlbumDTO() {
        // Arrange
        AlbumDTO albumDTO = createSampleAlbumDTO();
        Song song = createSampleSong();
        when(songRepository.save(any())).thenReturn(song);
        when(albumRepository.findById(any())).thenReturn(Optional.of(new Album()));

        // Act
        AlbumDTO result = songService.saveSong(albumDTO);

        // Assert
        assertNotNull(result);
        assertEquals(song.getAlbum().getTitle(), result.getTitle());
        assertEquals(1, result.getSongs().size());
        assertEquals(song.getTitle(), result.getSongs().get(0).getTitle());
    }

    @Test
    void saveSong_NullSongs_ReturnsUnchangedAlbumDTO() {
        // Arrange
        AlbumDTO albumDTO = new AlbumDTO();

        // Act
        AlbumDTO result = songService.saveSong(albumDTO);

        // Assert
        assertEquals(albumDTO, result);
        verify(songRepository, never()).save(any());
    }

//    @Disabled
//    @Test
//    void getSongById_ValidId_ReturnsAlbumDTO() {
//        // Arrange
//        Long songId = 1L;
//        Song song = createSampleSong();
//        when(songRepository.findById(songId)).thenReturn(Optional.of(song));
//
//        // Act
//        Optional<AlbumDTO> result = songService.getSongById(songId);
//
//        // Assert
//        assertTrue(result.isPresent());
//        assertEquals(song.getAlbum().getTitle(), result.get().getTitle());
//        assertEquals(1, result.get().getSongs().size());
//        assertEquals(song.getTitle(), result.get().getSongs().get(0).getTitle());
//    }

    @Test
    void getSongById_InvalidId_ReturnsEmptyOptional() {
        // Arrange
        Long songId = 1L;
        when(songRepository.findById(songId)).thenReturn(Optional.empty());

        // Act
        Optional<AlbumDTO> result = songService.getSongById(songId);

        // Assert
        assertTrue(result.isEmpty());
    }

//    @Disabled
//    @Test
//    void updateSong_ValidInput_ReturnsUpdatedAlbumDTO() {
//        // Arrange
//        AlbumDTO albumDTO = createSampleAlbumDTO();
//        Song existingSong = createSampleSong();
//        when(songRepository.findById(any())).thenReturn(Optional.of(existingSong));
//        when(songRepository.save(any())).thenReturn(existingSong);
//
//        // Act
//        Optional<AlbumDTO> result = songService.updateSong(albumDTO);
//
//        // Assert
//        assertTrue(result.isPresent());
//        assertEquals(albumDTO.getSongs().get(0).getTitle(), result.get().getSongs().get(0).getTitle());
//    }


    @Test
    void updateSong_InvalidSongId_ReturnsEmptyOptional() {
        // Arrange
        AlbumDTO albumDTO = createSampleAlbumDTO();
        when(songRepository.findById(any())).thenReturn(Optional.empty());

        // Act
        Optional<AlbumDTO> result = songService.updateSong(albumDTO);

        // Assert
        assertTrue(result.isEmpty());
        verify(songRepository, never()).save(any());
    }

    @Test
    void deleteSong_ValidId_DeletesSong() {
        // Arrange
        Long songId = 1L;

        // Act
        songService.deleteSong(songId);

        // Assert
        verify(songRepository, times(1)).deleteById(songId);
    }

//    @Disabled
//    @Test
//    void getAllSongs_ReturnsListOfAlbumDTOs() {
//        // Arrange
//        Iterable<Song> songs = createSampleSongsIterable();
//        when(songRepository.findAll()).thenReturn(songs);
//
//        // Act
//        List<AlbumDTO> result = songService.getAllSongs(1L);
//
//        // Assert
//        assertNotNull(result);
//        assertEquals(2, result.size());
//    }

    @Test
    void getSongsByAlbum_ValidAlbumId_ReturnsListOfSongs() {
        // Arrange
        Long albumId = 1L;
        List<Song> songs = createSampleSongsList();
        when(songRepository.findByAlbumId(albumId)).thenReturn(songs);

        // Act
        List<Song> result = songService.getSongsByAlbum(albumId);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    // Helper methods for creating sample data
    private AlbumDTO createSampleAlbumDTO() {
        SongDTO songDTO = SongDTO.builder()
                .title("Test Song")
                .artist("Test Artist")
                .isExplicit(true)
                .duration(3.5)
                .build();

        AlbumDTO albumDTO = new AlbumDTO();
        albumDTO.setId(1L);
        albumDTO.setTitle("Test Album");
        albumDTO.setArtist("Test Artist");
        albumDTO.setYear(2022);
        albumDTO.setGenre("Rock");
        albumDTO.setUser(new User());
        albumDTO.setSongs(List.of(songDTO));

        return albumDTO;
    }

    private Song createSampleSong() {
        Song song = new Song();
        song.setId(1L);
        song.setTitle("Test Song");
        song.setArtist("Test Artist");
        song.setExplicit(true);
        song.setDuration(3.5);
        song.setAlbum(new Album());

        return song;
    }

    private Iterable<Song> createSampleSongsIterable() {
        return List.of(createSampleSong(), createSampleSong());
    }

    private List<Song> createSampleSongsList() {
        return List.of(createSampleSong(), createSampleSong());
    }
}
