package com.example.countclicks;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class ClickCounterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void healthEndpointReportsApplicationIsUp() throws Exception {
        mockMvc.perform(get("/api/health"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("UP")))
                .andExpect(jsonPath("$.application", is("countclicks")));
    }

    @Test
    void clicksCanBeReadAndIncremented() throws Exception {
        mockMvc.perform(get("/api/clicks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count", is(0)));

        mockMvc.perform(post("/api/clicks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count", is(1)));

        mockMvc.perform(get("/api/clicks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count", is(1)));
    }
}
