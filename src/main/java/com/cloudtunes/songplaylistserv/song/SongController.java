package com.cloudtunes.songplaylistserv.song;

import com.cloudtunes.songplaylistserv.album.AlbumDTO;
import com.cloudtunes.songplaylistserv.user.User;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/song")
public class SongController {
    public final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping("/test")
    public String hello() {
        return "Hello";
    }

//    @RolesAllowed("ADMIN")
//    @GetMapping()
//    public ResponseEntity<List<AlbumDTO>> getAllSongs(@RequestHeader("Authorization") String token) {
//        AccessToken accessToken = accessTokenEncoderDecoder.decode(token.replace("Bearer ", ""));
//        return ResponseEntity.ok(songService.getAllSongs(accessToken.getUserId()));
//    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<AlbumDTO>> getSongById(@PathVariable Long id) {
        return ResponseEntity.ok(songService.getSongById(id));
    }

//    @RolesAllowed({"ADMIN", "MEMBER"})
//    @PostMapping()
//    public ResponseEntity<AlbumDTO> addSong(@RequestBody AlbumDTO albumDTO, @RequestHeader("Authorization") String token) {
//        //TODO: Add user validation
//        //AccessToken accessToken = accessTokenEncoderDecoder.decode(token.replace("Bearer ", ""));
//        //albumDTO.setUser(User.builder().id(accessToken.getUserId()).build());
//        return ResponseEntity.ok(songService.saveSong(albumDTO));
//    }

//    @RolesAllowed({"ADMIN", "MEMBER"})
//    @PutMapping()
//    public ResponseEntity<Optional<AlbumDTO>> updateSong(@RequestBody AlbumDTO albumDTO, @RequestHeader("Authorization") String token) {
//        //TODO: Add user validation
//       // AccessToken accessToken = accessTokenEncoderDecoder.decode(token.replace("Bearer ", ""));
//        albumDTO.setUser(User.builder().id(accessToken.getUserId()).build());
//        Optional<AlbumDTO> update = songService.updateSong(albumDTO);
//        if (update.isEmpty()) {
//            return ResponseEntity.badRequest().build();
//        }
//        return ResponseEntity.ok(update);
//    }
//
//    @RolesAllowed({"ADMIN", "MEMBER"})
//    @DeleteMapping()
//    public ResponseEntity deleteSong(@RequestParam Long id, @RequestHeader("Authorization") String token) {
//        //TODO: Add user validation
//        //AccessToken accessToken = accessTokenEncoderDecoder.decode(token.replace("Bearer ", ""));
//        songService.deleteSong(id);
//        return ResponseEntity.ok().build();
//    }
}
