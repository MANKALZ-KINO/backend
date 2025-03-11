package com.example.backend.controllers;

import com.example.backend.model.Movie;
import com.example.backend.repositories.IMovieRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class IMovieRepositoryTest {

    @Autowired
    IMovieRepository iMovieRepository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    void findAllbyName(){
        // Insert test data into H2
        Movie movie = new Movie();
        movie.setGenre("Drama");
        movie.setMovieName("Titanic");
        movie.setAgeLimit(13);
        movie.setDuration(195);
        iMovieRepository.save(movie);

        List<Movie> list = iMovieRepository.findAllBymovieName("Titanic");
        assertEquals(1, list.size());
    }

    @Test
    void createMovie(){
        Movie newMovie = new Movie();
        newMovie.setMovieId(null);
        newMovie.setGenre("Action");
        newMovie.setMovieName("Test Movie");
        newMovie.setAgeLimit(18);
        newMovie.setDuration(120);
        newMovie.setImageUrl("http://example.com/test-movie.jpg");

        Movie insertedMovie = iMovieRepository.save(newMovie);

        Movie savedMovie = entityManager.find(Movie.class, insertedMovie.getMovieId());
        assertThat(savedMovie).isEqualTo(insertedMovie);
    }

    @Test
    void updateMovie(){
        Movie movie = new Movie();
        entityManager.persist(movie);
        movie.setGenre("Thriller");
        movie.setMovieName("Test Movie1");
        movie.setAgeLimit(18);
        movie.setDuration(122);
        movie.setImageUrl("http://example.com/test-movie.jpg");

        entityManager.flush();

        String newName = "Test Movie2";
        movie.setMovieName(newName);
        iMovieRepository.save(movie);

        Movie updatedMovie = entityManager.find(Movie.class, movie.getMovieId());
        assertThat(updatedMovie.getMovieName()).isEqualTo(newName);
    }

    @Test
    void deleteMovie(){
        Movie movie = new Movie();
        movie.setMovieId(null);
        movie.setGenre("Action");
        movie.setMovieName("Test Movie");
        movie.setAgeLimit(18);
        movie.setDuration(120);
        movie.setImageUrl("http://example.com/test-movie.jpg");

        entityManager.persist(movie);
        iMovieRepository.delete(movie);
        assertThat(entityManager.find(Movie.class, movie.getMovieId())).isEqualTo(null);
    }

}