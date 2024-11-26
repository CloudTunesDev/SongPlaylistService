package com.cloudtunes.songplaylistserv.playlist;

public class PlaylistConverter {

    public static PlaylistDTO ToPlaylistDTO(Playlist playlist) {
        if (playlist == null)
            return null;

//        User owner = playlist.getUser();
//        if (owner != null) {
//            owner.setPassword(null);
//        }

        return PlaylistDTO.builder()
                .Id(playlist.getId())
                .title(playlist.getTitle())
//                .user(owner)
                .build();
    }

    public static Playlist ToEntity(PlaylistDTO playlistDTO) {
        if (playlistDTO == null)
            return null;

        Playlist playlist = new Playlist();
        playlist.setId(playlistDTO.getId());
        playlist.setTitle(playlistDTO.getTitle());
//        playlist.setUser(playlistDTO.getUser());
        return playlist;
    }
}

