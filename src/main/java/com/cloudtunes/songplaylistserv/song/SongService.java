package com.cloudtunes.songplaylistserv.song;

import com.cloudtunes.songplaylistserv.album.Album;
import com.cloudtunes.songplaylistserv.album.AlbumConverter;
import com.cloudtunes.songplaylistserv.album.AlbumDTO;
import com.cloudtunes.songplaylistserv.album.AlbumRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SongService {

    private final SongRepository songRepository;
    private final AlbumRepository albumRepository;

    private AlbumDTO ToDTO(Song song) {

        Optional<Album> albumBySong = albumRepository.findById(song.getAlbum().getId());
        AlbumDTO convertedAlbum = AlbumConverter.ToAlbumDTO(albumBySong.get());
        SongDTO convertedSong = SongConverter.ToSongDTO(song);
        convertedAlbum.setSongs(new ArrayList<>(){ { add(convertedSong); } });

        return convertedAlbum;
    }

    private Optional<Song> ToEntity (AlbumDTO albumDTO) {
        Song song = SongConverter.ToEntity(albumDTO.getSongs().get(0));
        Optional<Album> albumById = albumRepository.findById(albumDTO.getId());
        if (albumById.isEmpty()){
            return Optional.empty();
        }
        song.setAlbum(albumById.get());
        return Optional.of(song);
    }

    public SongService(SongRepository songRepository, AlbumRepository albumRepository) {
        this.songRepository = songRepository;
        this.albumRepository = albumRepository;
    }

    public AlbumDTO saveSong(AlbumDTO albumDTO) {
        if(albumDTO.getSongs() == null || albumDTO.getSongs().isEmpty()) {
            return albumDTO;
        }
        return ToDTO(songRepository.save(ToEntity(albumDTO).get()));
    }

    public Optional<AlbumDTO> getSongById(Long id) {
        Optional<Song> songById = songRepository.findById(id);
        if (songById.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(ToDTO(songById.get()));
    }
    public Optional<AlbumDTO> updateSong(AlbumDTO albumDTO) {
        Optional<Song> songById = songRepository.findById(albumDTO.getSongs().get(0).getId());
        if (songById.isEmpty()){
            return Optional.empty();
        }
        Song song = songById.get();
        song.setTitle(albumDTO.getSongs().get(0).getTitle());
        song.setArtist(albumDTO.getSongs().get(0).getArtist());
        song.setExplicit(albumDTO.getSongs().get(0).isExplicit());
        song.setDuration(albumDTO.getSongs().get(0).getDuration());
        return Optional.of(ToDTO(songRepository.save(song)));
    }

    public void deleteSong(Long id) {
        songRepository.deleteById(id);
    }

    public List<AlbumDTO> getAllSongs(Long userId) {
        Iterable<Song> songs = songRepository.findAll();
        return StreamSupport.stream(songs.spliterator(), false)
                .map(x -> ToDTO(x))
                .collect(Collectors.toList());
    }

    public List<Song> getSongsByAlbum(Long albumId) {
        return songRepository.findByAlbumId(albumId);
    }
}

