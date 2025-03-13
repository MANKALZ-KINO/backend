package com.example.backend.controller;

import com.example.backend.controllers.TheaterController;
import com.example.backend.model.Theater;
import com.example.backend.service.TheaterService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

// Brug @ExtendWith for JUnit 5
@ExtendWith(SpringExtension.class)
@WebMvcTest(TheaterController.class)
@Import(TheaterControllerTest.MockConfig.class)
public class TheaterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TheaterService theaterService;

    @Test
    void testGetAllTheaters() throws Exception {
        Theater theater1 = new Theater();
        Theater theater2 = new Theater();
        List<Theater> theaters = Arrays.asList(theater1, theater2);

        when(theaterService.getAllTheathers()).thenReturn(theaters);

        mockMvc.perform(MockMvcRequestBuilders.get("/theater/all"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2)); //tester for kun et objekt
    }

    @Test
    void testTheaterById() throws Exception {
        Theater theater1 = new Theater();
        List<Theater> theaters = List.of(theater1);

        when(theaterService.findTheatherById(1L)).thenReturn(theaters);

        mockMvc.perform(MockMvcRequestBuilders.get("/theater/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(1));
    }

    // Brug en separat mock-konfiguration da service ikke kunne mockes på alm. vis
    static class MockConfig {
        @Bean
        public TheaterService theaterService() {
            return Mockito.mock(TheaterService.class);
        }
    }
}
