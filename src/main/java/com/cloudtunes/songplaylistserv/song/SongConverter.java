package com.cloudtunes.songplaylistserv.song;

public class SongConverter {
    public static SongDTO ToSongDTO(Song song) {
        if (song == null)
            return null;

        return SongDTO.builder()
                .Id(song.getId())
                .title(song.getTitle())
                .artist(song.getArtist())
                .isExplicit(song.isExplicit())
                .duration(song.getDuration())
                .build();
    }

    public static Song ToEntity(SongDTO songDTO) {
        if (songDTO == null)
            return null;

        Song song = new Song();
        song.setTitle(songDTO.getTitle());
        song.setArtist(songDTO.getArtist());
        song.setExplicit(songDTO.isExplicit());
        song.setDuration(songDTO.getDuration());
        return song;
    }
}
