package com.cloudtunes.songplaylistserv.album;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AlbumRepository extends CrudRepository<Album, Long> {
    List<Album> findByTitle(String title);

    List<Album> findByTitleAndArtist(String title, String artist);

    List<Album> findByArtist(String artist);

    List<Album> findByGenre(String genre);

    List<Album> findByYear(int year);

//    List<Album> findByUserId(Long userId);

//    @Query("SELECT COUNT(a) FROM Album a WHERE a.user.id = :userId")
//    Long countAllAlbumsByUserId(@Param("userId") Long userId);
}
