package com.cloudtunes.songplaylistserv.album;

import com.cloudtunes.songplaylistserv.song.Song;
import com.cloudtunes.songplaylistserv.song.SongRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AlbumService {
    //Repo Initialization
    private final AlbumRepository albumRepository;
    private final SongRepository songRepository;

    public AlbumService(AlbumRepository albumRepository, SongRepository songRepository) {
        this.albumRepository = albumRepository;
        this.songRepository = songRepository;
    }

    // Gets

    public Optional<AlbumDTO> getAlbumById(Long id) {
        return albumRepository.findById(id).map(AlbumConverter::ToAlbumDTO);
    }

    public List<AlbumDTO> getAllAlbums() {
        Iterable<Album> albums = albumRepository.findAll();
        return StreamSupport.stream(albums.spliterator(), false)
                .map(AlbumConverter::ToAlbumDTO)
                .collect(Collectors.toList());
    }

//    public List<AlbumDTO> getAllUserAlbums(long userId) {
//        Iterable<Album> albums = albumRepository.findByUserId(userId);
//        return StreamSupport.stream(albums.spliterator(), false)
//                .map(AlbumConverter::ToAlbumDTO)
//                .collect(Collectors.toList());
//    }

//    public Long getCountedUserAlbums(Long userId) {
//        return albumRepository.countAllAlbumsByUserId(userId);
//    }

    // Post
    public AlbumDTO saveAlbum(AlbumDTO albumDTO) {
        Album album = AlbumConverter.ToEntity(albumDTO);
        Album savedAlbum = albumRepository.save(album);
        return AlbumConverter.ToAlbumDTO(savedAlbum);
    }

    // Put
    public Optional<AlbumDTO> updateAlbum(AlbumDTO albumDTO) {
        Optional<Album> albumById = albumRepository.findById(albumDTO.getId());
        if (albumById.isEmpty()){
            return Optional.empty();
        }
        Album album = albumById.get();
        album.setTitle(albumDTO.getTitle());
        album.setArtist(albumDTO.getArtist());
        album.setYear(albumDTO.getYear());
        album.setGenre(albumDTO.getGenre());

        return Optional.of(AlbumConverter.ToAlbumDTO(albumRepository.save(album)));
    }

    // Delete
    public void deleteAlbum(Long id) {
        List<Song> allSongsFromAlbum = songRepository.findByAlbumId(id);
        for (Song song : allSongsFromAlbum) {
            songRepository.deleteById(song.getId());
        }
        albumRepository.deleteById(id);
    }

}

