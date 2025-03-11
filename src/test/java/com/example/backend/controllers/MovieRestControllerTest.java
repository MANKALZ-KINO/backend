package com.example.backend.controllers;

import com.example.backend.model.Movie;
import com.example.backend.service.MovieService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MovieRestController.class)
@ExtendWith(MockitoExtension.class)
class MovieRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetMovies() throws Exception {
        Movie movie1 = new Movie();
        movie1.setMovieId(null);
        movie1.setGenre("Action");
        movie1.setMovieName("Test Movie");
        movie1.setAgeLimit(18);
        movie1.setDuration(120);
        movie1.setImageUrl("http://example.com/test-movie.jpg");
        Movie movie2 = new Movie();
        movie2.setMovieId(null);
        movie2.setGenre("Action");
        movie2.setMovieName("Inception");
        movie2.setAgeLimit(18);
        movie2.setDuration(120);
        movie2.setImageUrl("http://example.com/test-movie.jpg");

        when(movieService.findAllMovies()).thenReturn(Arrays.asList(movie1, movie2));

        mockMvc.perform(get("/movies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].movieName").value("Test Movie"))
                .andExpect(jsonPath("$[1].movieName").value("Inception"));
    }

    @Test
    void testGetMovieById() throws Exception {
        Movie movie = new Movie();
        movie.setMovieId(1L);
        movie.setGenre("Action");
        movie.setMovieName("Interstellar");
        movie.setAgeLimit(18);
        movie.setDuration(120);
        movie.setImageUrl("http://example.com/test-movie.jpg");
        when(movieService.findMovieById(1L)).thenReturn(Optional.of(movie));

        mockMvc.perform(get("/movies/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.movieName").value("Interstellar"));
    }

   @Test
    void testPostMovie() throws Exception {
        Movie movie = new Movie();
       movie.setMovieId(null);
       movie.setGenre("Action");
       movie.setMovieName("Dune");
       movie.setAgeLimit(18);
       movie.setDuration(120);
       movie.setImageUrl("http://example.com/test-movie.jpg");
        mockMvc.perform(post("/createmovie")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(movie)))
                .andExpect(status().isCreated());

        Mockito.verify(movieService).saveMovie(any(Movie.class));
    }

    @Test
    void testUpdateMovie_Success() throws Exception {
        Movie movie = new Movie();
        movie.setMovieId(1L);
        movie.setGenre("Action");
        movie.setMovieName("Interstellar Updated");
        movie.setAgeLimit(18);
        movie.setDuration(120);
        movie.setImageUrl("http://example.com/test-movie.jpg");
        when(movieService.findMovieById(1L)).thenReturn(Optional.of(movie));

        mockMvc.perform(put("/updatemovie")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(movie)))
                .andExpect(status().isOk());

        Mockito.verify(movieService).saveMovie(any(Movie.class));
    }

    @Test
    void testUpdateMovie_NotFound() throws Exception {
        Movie movie = new Movie();
        movie.setMovieId(99L);
        movie.setGenre("Action");
        movie.setMovieName("Unkown Film");
        movie.setAgeLimit(18);
        movie.setDuration(120);
        movie.setImageUrl("http://example.com/test-movie.jpg");
        when(movieService.findMovieById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(put("/updatemovie")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(movie)))
                .andExpect(status().isNotFound());
    }
}
