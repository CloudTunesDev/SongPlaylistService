package com.cloudtunes.songplaylistserv.album.integration;

import com.cloudtunes.songplaylistserv.album.AlbumController;
import com.cloudtunes.songplaylistserv.album.AlbumDTO;
import com.cloudtunes.songplaylistserv.album.AlbumService;
import com.cloudtunes.songplaylistserv.user.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.mockito.Mockito.when;

@WebMvcTest(controllers = AlbumController.class)
public class AlbumControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AlbumService albumService;

//    @MockBean
//    private AccessTokenEncoderDecoderImpl accessTokenEncoderDecoder;

    @Test
    public void testGetAllAlbums() throws Exception {
        // Mock data
        AlbumDTO album1DTO = createAlbumDTO(1L, "Album 1", "Artist 1", 2022, "Pop");
        AlbumDTO album2DTO = createAlbumDTO(2L, "Album 2", "Artist 2", 2023, "Rock");

        when(albumService.getAllAlbums()).thenReturn(java.util.Arrays.asList(album1DTO, album2DTO));

        // Perform the request and verify the response
        mockMvc.perform(MockMvcRequestBuilders.get("/album"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));
    }

    @Test
    public void testGetAlbumById() throws Exception {
        // Mock data
        Long albumId = 1L;
        AlbumDTO albumDTO = createAlbumDTO(albumId, "Album 1", "Artist 1", 2022, "Pop");

        when(albumService.getAlbumById(albumId)).thenReturn(Optional.of(albumDTO));

        // Perform the request and verify the response
        mockMvc.perform(MockMvcRequestBuilders.get("/album/{id}", albumId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(albumId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Album 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.artist").value("Artist 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.year").value(2022))
                .andExpect(MockMvcResultMatchers.jsonPath("$.genre").value("Pop"));
    }

    @Test
    public void testGetAllUserAlbums() throws Exception {
        // Mock data
        Long userId = 1L;
        String token = "Bearer access_token";
        User currentUser = User.builder().id(userId).build();
        AlbumDTO album1DTO = createAlbumDTO(1L, "Album 1", "Artist 1", 2022, "Pop");
        AlbumDTO album2DTO = createAlbumDTO(2L, "Album 2", "Artist 2", 2023, "Rock");

        //when(accessTokenEncoderDecoder.decode("access_token")).thenReturn(new AccessToken("username", userId, Collections.singletonList("ADMIN")));
        when(albumService.getAllUserAlbums(userId)).thenReturn(java.util.Arrays.asList(album1DTO, album2DTO));

        // Perform the request and verify the response
        mockMvc.perform(MockMvcRequestBuilders.get("/album/userAlbums").header("Authorization", token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));
    }

    @Test
    public void testGetUserAlbumCount() throws Exception {
        // Mock data
        Long userId = 1L;
        String token = "Bearer access_token";

        //when(accessTokenEncoderDecoder.decode("access_token")).thenReturn(new AccessToken("username", userId, Collections.singletonList("ADMIN")));
        when(albumService.getCountedUserAlbums(userId)).thenReturn(3L);

        // Perform the request and verify the response
        mockMvc.perform(MockMvcRequestBuilders.get("/album/userAlbumCount").header("Authorization", token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(3));
    }

    @Test
    public void testAddAlbum() throws Exception {
        // Mock data
        Long userId = 1L;
        String token = "Bearer access_token";
        AlbumDTO albumDTO = createAlbumDTO(null, "New Album", "New Artist", 2024, "R&B");

        //when(accessTokenEncoderDecoder.decode("access_token")).thenReturn(new AccessToken("username", userId, Collections.singletonList("ADMIN")));
        when(albumService.saveAlbum(albumDTO)).thenReturn(createAlbumDTO(1L, "New Album", "New Artist", 2024, "R&B"));

        // Perform the request and verify the response
        mockMvc.perform(MockMvcRequestBuilders.post("/album")
                        .content(objectMapper.writeValueAsString(albumDTO))
                        .header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("New Album"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.artist").value("New Artist"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.year").value(2024))
                .andExpect(MockMvcResultMatchers.jsonPath("$.genre").value("R&B"));
    }

    @Test
    public void testUpdateAlbum() throws Exception {
        // Mock data
        AlbumDTO albumDTO = createAlbumDTO(1L, "Updated Album", "Updated Artist", 2025, "Pop");

        when(albumService.updateAlbum(albumDTO)).thenReturn(Optional.of(albumDTO));

        // Perform the request and verify the response
        mockMvc.perform(MockMvcRequestBuilders.put("/album")
                        .content(objectMapper.writeValueAsString(albumDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Updated Album"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.artist").value("Updated Artist"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.year").value(2025))
                .andExpect(MockMvcResultMatchers.jsonPath("$.genre").value("Pop"));
    }

    @Test
    public void testDeleteAlbum() throws Exception {
        // Mock data
        Long albumId = 1L;

        // Perform the request and verify the response
        mockMvc.perform(MockMvcRequestBuilders.delete("/album").param("id", albumId.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    // Helper method to create an AlbumDTO
    private AlbumDTO createAlbumDTO(Long id, String title, String artist, int year, String genre) {
        return AlbumDTO.builder()
                .id(id)
                .title(title)
                .artist(artist)
                .year(year)
                .genre(genre)
                .user(null)  // user and songs are not required for testing AlbumController
                .songs(null)
                .build();
    }
}
