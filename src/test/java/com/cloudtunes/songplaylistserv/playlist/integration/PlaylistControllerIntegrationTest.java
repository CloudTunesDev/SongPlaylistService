package com.cloudtunes.songplaylistserv.playlist.integration;

import com.cloudtunes.songplaylistserv.playlist.PlaylistDTO;
import com.cloudtunes.songplaylistserv.playlist.PlaylistService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
class PlaylistControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PlaylistService playlistService;

    @Test
    void testGetAllPlaylists() throws Exception {
        // Mock data
        PlaylistDTO playlist1DTO = createPlaylistDTO(1L, "Playlist 1");
        PlaylistDTO playlist2DTO = createPlaylistDTO(2L, "Playlist 2");

        when(playlistService.getAllPlaylists()).thenReturn(java.util.Arrays.asList(playlist1DTO, playlist2DTO));

        // Perform the request and verify the response
        mockMvc.perform(MockMvcRequestBuilders.get("/playlist"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));
    }

//    @Test
//    void testGetPlaylistById() throws Exception {
//        // Mock data
//        Long playlistId = 1L;
//        PlaylistDTO playlistDTO = createPlaylistDTO(playlistId, "Playlist 1");
//
//        when(playlistService.getPlaylistById(playlistId)).thenReturn(Optional.of(playlistDTO));
//
//        // Perform the request and verify the response
//        mockMvc.perform(MockMvcRequestBuilders.get("/playlist/{id}", playlistId))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(playlistId))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Playlist 1"));
//    }

//    @Test
//    void testAddPlaylist() throws Exception {
//        // Mock data
//        PlaylistDTO playlistDTO = createPlaylistDTO(null, "New Playlist");
//
//        when(playlistService.savePlaylist(playlistDTO)).thenReturn(createPlaylistDTO(1L, "New Playlist"));
//
//        // Perform the request and verify the response
//        mockMvc.perform(MockMvcRequestBuilders.post("/playlist")
//                        .content(objectMapper.writeValueAsString(playlistDTO))
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("New Playlist"));
//    }

//    @Test
//    void testUpdatePlaylist() throws Exception {
//        // Mock data
//        PlaylistDTO playlistDTO = createPlaylistDTO(1L, "Updated Playlist");
//
//        when(playlistService.updatePlaylist(playlistDTO)).thenReturn(Optional.of(playlistDTO));
//
//        // Perform the request and verify the response
//        mockMvc.perform(MockMvcRequestBuilders.put("/playlist")
//                        .content(objectMapper.writeValueAsString(playlistDTO))
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Updated Playlist"));
//    }

    @Test
    void testDeletePlaylist() throws Exception {
        // Mock data
        Long playlistId = 1L;

        // Perform the request and verify the response
        mockMvc.perform(MockMvcRequestBuilders.delete("/playlist").param("id", playlistId.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    // Helper method to create a PlaylistDTO
    private PlaylistDTO createPlaylistDTO(Long id, String name) {
        return PlaylistDTO.builder()
                .Id(id)
                .title(name)
                .build();
    }
}