package com.cloudtunes.songplaylistserv.playlist.unit;

import com.cloudtunes.songplaylistserv.playlist.Playlist;
import com.cloudtunes.songplaylistserv.playlist.PlaylistDTO;
import com.cloudtunes.songplaylistserv.playlist.PlaylistRepository;
import com.cloudtunes.songplaylistserv.playlist.PlaylistService;
import com.cloudtunes.songplaylistserv.playlist.playlistsongcombo.PlaylistSong;
import com.cloudtunes.songplaylistserv.playlist.playlistsongcombo.PlaylistSongRepository;
import com.cloudtunes.songplaylistserv.song.Song;
import com.cloudtunes.songplaylistserv.song.SongDTO;
import com.cloudtunes.songplaylistserv.song.SongRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PlaylistServiceTests {

    private PlaylistService playlistService;

    @Mock
    private PlaylistRepository playlistRepository;

    @Mock
    private SongRepository songRepository;

    @Mock
    private PlaylistSongRepository playlistSongRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        playlistService = new PlaylistService(playlistRepository, songRepository, playlistSongRepository);
    }

    @Test
    void testToDTO() {
        // Arrange
        Playlist playlist = new Playlist();  // Create a Playlist object with some dummy data
        playlist.setId(1L);
        playlist.setTitle("Test Playlist");

        PlaylistSong playlistSong = new PlaylistSong();  // Create a PlaylistSong object with some dummy data
        playlistSong.setSong(new Song());  // You may need to set some dummy data for Song as well
        playlistSong.getSong().setId(1L);

        when(playlistSongRepository.findByPlaylistId(1L)).thenReturn(Arrays.asList(playlistSong));

        // Act
        PlaylistDTO playlistDTO = playlistService.ToDTO(playlist);

        // Assert
        assertEquals(playlist.getId(), playlistDTO.getId());
        assertEquals(playlist.getTitle(), playlistDTO.getTitle());

        List<SongDTO> songDTOs = playlistDTO.getSongList();
        assertEquals(1, songDTOs.size());

        SongDTO songDTO = songDTOs.get(0);
        assertEquals(playlistSong.getSong().getId(), songDTO.getId());

        // Verify that the repository methods were called
        verify(playlistSongRepository, times(1)).findByPlaylistId(1L);
        verifyNoMoreInteractions(playlistSongRepository);
    }

    @Test
    void testSavePlaylist() {
        // Arrange
        PlaylistDTO playlistDTO = new PlaylistDTO();
        playlistDTO.setTitle("Test Playlist");
        Playlist playlist = new Playlist();
        playlist.setId(1L);
        playlist.setTitle("Test Playlist");

        when(playlistRepository.save(any(Playlist.class))).thenReturn(playlist);

        // Act
        PlaylistDTO savedPlaylistDTO = playlistService.savePlaylist(playlistDTO);

        // Assert
        assertEquals(playlist.getId(), savedPlaylistDTO.getId());
        assertEquals(playlist.getTitle(), savedPlaylistDTO.getTitle());
    }

    @Test
    void testSaveSongToPlaylist() {
        // Arrange
        Long songId = 1L;
        Long playlistId = 1L;
        Song song = new Song();
        song.setId(songId);
        Playlist playlist = new Playlist();
        playlist.setId(playlistId);

        when(songRepository.findById(songId)).thenReturn(Optional.of(song));
        when(playlistRepository.findById(playlistId)).thenReturn(Optional.of(playlist));

        // Act
        playlistService.saveSongToPlaylist(songId, playlistId);

        // Assert
        verify(playlistSongRepository, times(1)).save(any(PlaylistSong.class));
    }

    @Test
    void testRemoveSongFromPlaylist() {
        // Arrange
        Long songId = 1L;
        Long playlistId = 1L;
        Song song = new Song();
        song.setId(songId);
        Playlist playlist = new Playlist();
        playlist.setId(playlistId);

        when(songRepository.findById(songId)).thenReturn(Optional.of(song));
        when(playlistRepository.findById(playlistId)).thenReturn(Optional.of(playlist));

        // Act
        playlistService.deleteSongFromPlaylist(songId, playlistId);

    }

    @Test
    void testGetAllPlaylists() {
        // Arrange
        Playlist playlist1 = new Playlist();
        playlist1.setId(1L);
        playlist1.setTitle("Playlist 1");

        Playlist playlist2 = new Playlist();
        playlist2.setId(2L);
        playlist2.setTitle("Playlist 2");

        when(playlistRepository.findAll()).thenReturn(Arrays.asList(playlist1, playlist2));

        // Act
        Iterable<PlaylistDTO> result = playlistService.getAllPlaylists();

        // Assert
        Iterator<PlaylistDTO> iterator = result.iterator();

        assertEquals(playlist1.getId(), iterator.next().getId());
        assertEquals(playlist2.getId(), iterator.next().getId());

        // Verify that the repository method was called
        verify(playlistRepository, times(1)).findAll();
        verifyNoMoreInteractions(playlistRepository);
    }
}