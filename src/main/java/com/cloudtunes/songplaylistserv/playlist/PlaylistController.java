package com.cloudtunes.songplaylistserv.playlist;

import jakarta.annotation.security.RolesAllowed;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/playlist")
public class PlaylistController {
    public final PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @RolesAllowed("ADMIN")
    @GetMapping
    public ResponseEntity<Iterable<PlaylistDTO>> getAllPlaylists() {
        return ResponseEntity.ok(playlistService.getAllPlaylists());
    }

    @RolesAllowed({"ADMIN", "MEMBER"})
    @GetMapping("/unassignedSongPlaylists")
    public ResponseEntity<Iterable<PlaylistDTO>> getSongUnassignedPlaylists(@RequestParam Long songId) {
        return ResponseEntity.ok(playlistService.getSongUnassignedPlaylists(songId));
    }

    @RolesAllowed({"ADMIN", "MEMBER", "GUEST"})
    @GetMapping("/{id}")
    public ResponseEntity<Optional<PlaylistDTO>> getPlaylistById(@PathVariable Long id) {
        return ResponseEntity.ok(playlistService.getPlaylistById(id));
    }

    @RolesAllowed({"ADMIN", "MEMBER"})
    @PostMapping()
    public ResponseEntity<PlaylistDTO> addPlaylist(@RequestBody PlaylistDTO playlist, @RequestHeader("Authorization") String token) {
        try {

            //playlist.setUser(User.builder().id(accessToken.getUserId()).build());
            return ResponseEntity.ok(playlistService.savePlaylist(playlist));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @RolesAllowed({"ADMIN", "MEMBER"})
    @PostMapping("/addSongToPlaylist")
    public ResponseEntity addSongToPlaylist(@RequestParam Long songId, @RequestParam Long playlistId) {
        playlistService.saveSongToPlaylist(songId, playlistId);
        return ResponseEntity.ok().build();
    }

    @RolesAllowed({"ADMIN", "MEMBER"})
    @PutMapping
    public ResponseEntity<Optional<PlaylistDTO>> updatePlaylist(@RequestBody PlaylistDTO playlist) {
        return ResponseEntity.ok(playlistService.updatePlaylist(playlist));
    }

    @RolesAllowed({"ADMIN", "MEMBER"})
    @DeleteMapping()
    public ResponseEntity deletePlaylist(@RequestParam Long id) {
        playlistService.deletePlaylist(id);
        return ResponseEntity.ok().build();
    }

    @RolesAllowed({"ADMIN", "MEMBER"})
    @DeleteMapping("/deleteSongFromPlaylist")
    public ResponseEntity deleteSongFromPlaylist(@RequestParam Long playlistId, @RequestParam Long songId) {
        playlistService.deleteSongFromPlaylist(playlistId, songId);
        return ResponseEntity.ok().build();
    }
}
