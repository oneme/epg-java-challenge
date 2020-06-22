package com.tdcs.epg;

import com.tdcs.epg.controllers.VideoGameRestController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class VideoGameRestControllerTest {

    @Autowired
    private VideoGameRestController videoGameRestController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(videoGameRestController).build();
    }

    @Test
    void whenCallAvailableVideoGames_thenReturns200() throws Exception {
        mockMvc.perform(get("/api/availableVideoGames")).andExpect(status().isOk());
    }

    @Test
    void whenCallVideoGames_thenReturns200() throws Exception {
        mockMvc.perform(get("/api/videoGames")).andExpect(status().isOk());
    }

}
