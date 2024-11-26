package com.cloudtunes.songplaylistserv.song;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SongRepository extends CrudRepository<Song, Long> {
    List<Song> findByAlbumId(Long id);
}

