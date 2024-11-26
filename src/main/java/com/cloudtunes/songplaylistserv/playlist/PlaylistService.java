package com.cloudtunes.songplaylistserv.playlist;


import com.cloudtunes.songplaylistserv.playlist.playlistsongcombo.PlaylistSong;
import com.cloudtunes.songplaylistserv.playlist.playlistsongcombo.PlaylistSongRepository;
import com.cloudtunes.songplaylistserv.song.Song;
import com.cloudtunes.songplaylistserv.song.SongConverter;
import com.cloudtunes.songplaylistserv.song.SongDTO;
import com.cloudtunes.songplaylistserv.song.SongRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlaylistService {

    private final PlaylistRepository playlistRepository;
    private final SongRepository songRepository;
    private final PlaylistSongRepository playlistSongRepository;


    public PlaylistService(PlaylistRepository playlistRepository, SongRepository songRepository, PlaylistSongRepository playlistSongRepository) {
        this.playlistRepository = playlistRepository;
        this.songRepository = songRepository;
        this.playlistSongRepository = playlistSongRepository;
    }

    public PlaylistDTO ToDTO(Playlist playlist) {
        List<SongDTO> songs = new ArrayList<>();
        List<PlaylistSong> songsByPlaylistId = playlistSongRepository.findByPlaylistId(playlist.getId());
        songsByPlaylistId.forEach(x -> songs.add(SongConverter.ToSongDTO(x.getSong())));

        PlaylistDTO playlistDTO = PlaylistConverter.ToPlaylistDTO(playlist);
        playlistDTO.setSongList(songs);

        return playlistDTO;
    }

    public PlaylistDTO savePlaylist(PlaylistDTO playlistDTO) {
        Playlist playlist = PlaylistConverter.ToEntity(playlistDTO);
        Playlist savedPlaylist = playlistRepository.save(playlist);
        return PlaylistConverter.ToPlaylistDTO(savedPlaylist);
    }

    public void saveSongToPlaylist(Long songId, Long playlistId) {
        Optional<Song> song = songRepository.findById(songId);
        Optional<Playlist> playlist = playlistRepository.findById(playlistId);
        if (song.isPresent() && playlist.isPresent()) {
            playlistSongRepository.save(PlaylistSong.builder()
                    .song(song.get())
                    .playlist(playlist.get())
                    .build());
        }
    }

    public Optional<PlaylistDTO> getPlaylistById(Long id) {
        Optional<Playlist> playlists = playlistRepository.findById(id);
        if(playlists.isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(ToDTO(playlists.get()));
    }

    public Iterable<PlaylistDTO> getSongUnassignedPlaylists(Long songId) {
        List<PlaylistDTO> playlistDTOS = new ArrayList<>();
        Iterable<Playlist> playlists = playlistRepository.findAll();
        List<PlaylistSong> assignedPlaylists = playlistSongRepository.findBySongId(songId);
//        for (Playlist playlist : playlists) {
//            if (!assignedPlaylists.stream().anyMatch(x -> x.getPlaylist().getId() == playlist.getId())) {
//                playlistDTOS.add(new PlaylistDTO(playlist.getId(), playlist.getTitle(), null, null));
//            }
//        }
        return playlistDTOS;
    }

    public void deletePlaylist(Long id) {
        List<PlaylistSong> allPlaylistSongs = playlistSongRepository.findByPlaylistId(id);
        for (PlaylistSong playlistSong : allPlaylistSongs) {
            playlistSongRepository.deleteById(playlistSong.getId());
        }
        playlistRepository.deleteById(id);
    }

    public void deleteSongFromPlaylist(Long playlistId, Long songId) {
        Optional<PlaylistSong> playlistSong = playlistSongRepository.findByPlaylistIdAndSongId(playlistId, songId);
        playlistSong.ifPresent(song -> playlistSongRepository.deleteById(song.getId()));
    }

    public Optional<PlaylistDTO> updatePlaylist(PlaylistDTO playlistDTO) {
        Optional<Playlist> playlistById = playlistRepository.findById(playlistDTO.getId());
        if (playlistById.isEmpty()){
            return Optional.empty();
        }
        Playlist playlist = playlistById.get();
        playlist.setTitle(playlistDTO.getTitle());

        return Optional.of(PlaylistConverter.ToPlaylistDTO(playlistRepository.save(playlist)));
    }

    public List<PlaylistDTO> getAllPlaylists() {
        Iterable<Playlist> playlists = playlistRepository.findAll();
        List<PlaylistDTO> playlistDTOS = new ArrayList<>();
        playlists.forEach(x -> playlistDTOS.add(ToDTO(x)));

        return playlistDTOS;
    }
}

