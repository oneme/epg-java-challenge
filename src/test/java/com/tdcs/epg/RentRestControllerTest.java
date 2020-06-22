package com.tdcs.epg;


import com.tdcs.epg.common.Constants;
import com.tdcs.epg.common.Utils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql("/test.sql")
@AutoConfigureMockMvc
public class RentRestControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void whenCallGetRentedVideoGamesByUserWithInValidInput_thenReturns400() throws Exception {
        String response = mockMvc.perform(get("/api/rented/{id}", -1))
                .andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();

        assertThat(response).isEqualTo(Constants.USER_NOT_FOUND);
    }

    @Test
    public void whenCallGetPriceWithValidInput_thenReturns200() throws Exception {
        mockMvc.perform(post("/api/rent/price").contentType(MediaType.APPLICATION_JSON)
                .content(Utils.prepareRequestForTest(1L, 1L, 1L)))
                .andExpect(status().isOk()).andExpect(jsonPath("$.[0].price", is(4)));
    }

    @Test
    public void whenCallGetPriceWithInvalidVideoGame_thenReturns400() throws Exception {
        String response = mockMvc.perform(post("/api/rent/price").contentType(MediaType.APPLICATION_JSON)
                .content(Utils.prepareRequestForTest(1L, -1L, 1L)))
                .andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();

        assertThat(response).isEqualTo(Constants.VIDEO_GAME_NOT_FOUND);
    }

    @Test
    public void whenCallGetPriceWithInvalidDaysAmount_thenReturns400() throws Exception {
        String response = mockMvc.perform(post("/api/rent/price").contentType(MediaType.APPLICATION_JSON)
                .content(Utils.prepareRequestForTest(1L, 1L, -1L)))
                .andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();

        assertThat(response).isEqualTo(Constants.NO_VALID_DAYS_AMOUNT);
    }

    @Test
    public void whenCallGetRentedVideoGamesByUserWithValidInput_thenReturns200() throws Exception {
        mockMvc.perform(get("/api/rented/{id}", 1))
                .andExpect(status().isOk()).andExpect(jsonPath("$.[0].videoGame.id", is(1)));;
    }

    @Test
    public void whenCallRentWithValidInput_thenReturns200() throws Exception {
        String response = mockMvc.perform(post("/api/rent").contentType(MediaType.APPLICATION_JSON)
                .content(Utils.prepareRequestForTest(1L, 1L, 1L)))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        assertThat(response).isEqualTo(Constants.RENT_SUCCESS);
    }

    @Test
    public void whenCallRentWithInValidUser_thenReturns400() throws Exception {
        String response = mockMvc.perform(post("/api/rent").contentType(MediaType.APPLICATION_JSON)
                .content(Utils.prepareRequestForTest(-1L, 1L, 1L)))
                .andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();

        assertThat(response).isEqualTo(Constants.USER_NOT_FOUND);
    }

    @Test
    public void whenCallRentWithInValidVideoGame_thenReturns400() throws Exception {
        String response = mockMvc.perform(post("/api/rent").contentType(MediaType.APPLICATION_JSON)
                .content(Utils.prepareRequestForTest(1L, -1L, 1L)))
                .andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();

        assertThat(response).isEqualTo(Constants.VIDEO_GAME_NOT_FOUND);
    }

    @Test
    public void whenCallRentWithInValidDaysAmount_thenReturns400() throws Exception {
        String response = mockMvc.perform(post("/api/rent").contentType(MediaType.APPLICATION_JSON)
                .content(Utils.prepareRequestForTest(1L, 1L, 0L)))
                .andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();

        assertThat(response).isEqualTo(Constants.NO_VALID_DAYS_AMOUNT);
    }

    @Test
    public void whenCallRentWithUnAvailableVideoGame_thenReturns400() throws Exception {
        String response = mockMvc.perform(post("/api/rent").contentType(MediaType.APPLICATION_JSON)
                .content(Utils.prepareRequestForTest(1L, 1L, 1L)))
                .andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();

        assertThat(response).isEqualTo(Constants.VIDEO_GAME_NOT_AVAILABLE);
    }

    @Test
    public void whenCallGiveBackValidInput_thenReturns200() throws Exception {
        String response = mockMvc.perform(post("/api/giveBack").contentType(MediaType.APPLICATION_JSON)
                .content(Utils.prepareRequestForTest(1L, 1L, 0L)))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        assertThat(response).isEqualTo(Constants.RETURN_SUCCESS);
    }

    @Test
    public void whenCallGiveBackWithInValidUser_thenReturns400() throws Exception {
        String response = mockMvc.perform(post("/api/giveBack").contentType(MediaType.APPLICATION_JSON)
                .content(Utils.prepareRequestForTest(-1L, 1L, 1L)))
                .andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();

        assertThat(response).isEqualTo(Constants.USER_NOT_FOUND);
    }

    @Test
    public void whenCallGiveBackWithInValidVideoGame_thenReturns400() throws Exception {
        String response = mockMvc.perform(post("/api/giveBack").contentType(MediaType.APPLICATION_JSON)
                .content(Utils.prepareRequestForTest(1L, -1L, 1L)))
                .andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();

        assertThat(response).isEqualTo(Constants.VIDEO_GAME_NOT_FOUND);
    }

    @Test
    public void whenCallGiveBackWithInValidDaysAmount_thenReturns400() throws Exception {
        String response = mockMvc.perform(post("/api/giveBack").contentType(MediaType.APPLICATION_JSON)
                .content(Utils.prepareRequestForTest(1L, 1L, -1L)))
                .andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();

        assertThat(response).isEqualTo(Constants.NO_VALID_DAYS_AMOUNT);
    }

    @Test
    public void whenCallGiveBackWithUnAvailableVideoGame_thenReturns400() throws Exception {
        String response = mockMvc.perform(post("/api/giveBack").contentType(MediaType.APPLICATION_JSON)
                .content(Utils.prepareRequestForTest(2L, 1L, 0L)))
                .andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();

        assertThat(response).isEqualTo(Constants.NO_RENTS_FOUND_FOR_THIS_USER);
    }
}
