package com.cloudtunes.songplaylistserv.playlist.playlistsongcombo;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PlaylistSongRepository extends CrudRepository<PlaylistSong, Long> {
    List<PlaylistSong> findByPlaylistId(Long id);
    List<PlaylistSong> findBySongId(Long id);
    Optional<PlaylistSong> findByPlaylistIdAndSongId(Long playlistId, Long songId);
}
