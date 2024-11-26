package com.cloudtunes.songplaylistserv.album;

import org.springframework.stereotype.Service;


@Service
public class AlbumConverter {
    public static AlbumDTO ToAlbumDTO(Album album) {
        if (album == null)
            return null;

//        User owner = album.getUser();
//        if (owner != null) {
//            owner.setPassword(null);
//        }

        return AlbumDTO.builder()
                .id(album.getId())
                .title(album.getTitle())
                .artist(album.getArtist())
                .year(album.getYear())
                .genre(album.getGenre())
//                .user(owner)
                .build();
    }

    public static Album ToEntity(AlbumDTO albumDTO) {
        if (albumDTO == null)
            return null;

        Album album = new Album();
        album.setTitle(albumDTO.getTitle());
        album.setArtist(albumDTO.getArtist());
        album.setYear(albumDTO.getYear());
        album.setGenre(albumDTO.getGenre());
        //album.setUser(albumDTO.getUser());

        return album;
    }
}
