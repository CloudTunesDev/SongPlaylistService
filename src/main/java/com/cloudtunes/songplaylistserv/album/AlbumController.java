package com.cloudtunes.songplaylistserv.album;

import com.cloudtunes.songplaylistserv.user.User;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/album")
public class AlbumController {
    public final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @RolesAllowed("ADMIN")
    @GetMapping
    public ResponseEntity<Iterable<AlbumDTO>> getAllAlbums() {
        return ResponseEntity.ok(albumService.getAllAlbums());
    }

    @RolesAllowed({"ADMIN", "MEMBER", "GUEST"})
    @GetMapping("/{id}")
    public ResponseEntity<Optional<AlbumDTO>> getAlbumById(@PathVariable Long id) {
        return ResponseEntity.ok(albumService.getAlbumById(id));
    }

//    @RolesAllowed({"ADMIN", "MEMBER"})
//    @GetMapping("/userAlbums")
//    public ResponseEntity<Iterable<AlbumDTO>> getAllUserAlbums(@RequestHeader("Authorization") String token) {
//        try {
//            //User currentUser = User.builder().id(accessToken.getUserId()).build();
//            return ResponseEntity.ok(albumService.getAllUserAlbums(currentUser.getId()));
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().build();
//        }
//    }

//    @RolesAllowed({"ADMIN", "MEMBER"})
//    @GetMapping("/userAlbumCount")
//    public ResponseEntity<Long> getUserAlbumCount(@RequestHeader("Authorization") String token) {
//        try {
//            User currentUser = User.builder().id(accessToken.getUserId()).build();
//            return ResponseEntity.ok(albumService.getCountedUserAlbums(currentUser.getId()));
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().build();
//        }
//    }

    @RolesAllowed({"ADMIN", "MEMBER"})
    @PostMapping()
    public ResponseEntity<AlbumDTO> addAlbum(@RequestBody AlbumDTO album, @RequestHeader("Authorization") String token) {
        try {
            //album.setUser(User.builder().id(accessToken.getUserId()).build());
            return ResponseEntity.ok(albumService.saveAlbum(album));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @RolesAllowed({"ADMIN", "MEMBER"})
    @PutMapping()
    public ResponseEntity<Optional<AlbumDTO>> updateAlbum(@RequestBody AlbumDTO albumDTO) {
        return ResponseEntity.ok(albumService.updateAlbum(albumDTO));
    }

    @RolesAllowed({"ADMIN", "MEMBER"})
    @DeleteMapping()
    public ResponseEntity deleteAlbum(@RequestParam Long id) {
        albumService.deleteAlbum(id);
        return ResponseEntity.ok().build();
    }
}
