package com.example.backend.service;

import com.example.backend.model.Movie;
import com.example.backend.repositories.IMovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final IMovieRepository iMovieRepository;

    public MovieService(IMovieRepository iMovieRepository) {
        this.iMovieRepository = iMovieRepository;
    }

    public List<Movie> findAllMovies() {
        return iMovieRepository.findAll();
    }

    public List<Movie> findAllMoviesByName(String name) {
        return iMovieRepository.findAllBymovieName(name);
    }
    public void saveMovie(Movie movie) {
        iMovieRepository.save(movie);
    }

    public Optional<Movie> findMovieById(Long movieId) {
        return iMovieRepository.findById(movieId);
    }

    public void deleteMovieById(Long movieId) {
        iMovieRepository.deleteById(movieId);
    }
}
