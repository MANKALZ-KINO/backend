package com.example.backend.controllers;

import com.example.backend.model.Movie;
import com.example.backend.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class MovieRestController {

    private final MovieService movieService;

    public MovieRestController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/")
    public String detteErRoden() {
        return "Du er i roden";
    }

    //GET
    @GetMapping("/movies")
    public List<Movie> movies() {
        return movieService.findAllMovies();
    }

    @GetMapping("/movies/{id}")
    public Movie findMovieByID(@PathVariable Long id) {
        return movieService.findMovieById(id).orElse(null);
    }

    //POST
    @PostMapping("/createmovie")
    @ResponseStatus(HttpStatus.CREATED)
    public void postMovie(@RequestBody Movie movie) {
        System.out.println("indsætter ny movie!!");
        movieService.saveMovie(movie);
    }

    //PUT
    @PutMapping("/updatemovie")
    public ResponseEntity<Movie> updateMovie(@RequestBody Movie movie) {
        Optional<Movie> orgMovie = movieService.findMovieById(movie.getMovieId());
        if (orgMovie.isPresent()) {
            movieService.saveMovie(movie);
            return new ResponseEntity<>(movie, HttpStatus.OK); //body er JSON
        } else {
            return new ResponseEntity<>(new Movie(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{movieId}")
    public ResponseEntity<?> deleteMovie(@PathVariable Long movieId) {
        try {
            movieService.deleteMovieById(movieId);
            return ResponseEntity.ok().body("Movie deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting movie: " + e.getMessage());
        }
    }
}
