package com.example.backend.config;

import com.example.backend.model.*;
import com.example.backend.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class MoviePlantInitData implements CommandLineRunner {

    @Autowired
    IMoviePlanRepository iMoviePlanRepository;

    @Autowired
    IMovieRepository iMovieRepository;

    @Autowired
    ITheaterRepository iTheaterRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // Undgå dubletter
        if (iMoviePlanRepository.count() == 0) {
            // Find eller opret en film
            Movie movie = iMovieRepository.findById(1L).orElseGet(() -> {
                Movie newMovie = new Movie();
                newMovie.setMovieName("Inception");
                newMovie.setDuration(148);
                return iMovieRepository.save(newMovie);
            });

            // Find eller opret en biografsal
            Theater theater = iTheaterRepository.findById(1L).orElseGet(() -> {
                Theater newTheater = new Theater();
                newTheater.setTheaterName("Grand Hall");
                newTheater.setCapacity(250);
                return iTheaterRepository.save(newTheater);
            });

            // Opret filmvisninger
            MoviePlan moviePlan1 = new MoviePlan();
            moviePlan1.setDate(LocalDate.now());
            moviePlan1.setShowNumber(ShowNumber.MORNING);
            moviePlan1.setMovie(movie);
            moviePlan1.setTheater(theater);

            MoviePlan moviePlan2 = new MoviePlan();
            moviePlan2.setDate(LocalDate.now().plusDays(1));
            moviePlan2.setShowNumber(ShowNumber.AFTERNOON);
            moviePlan2.setMovie(movie);
            moviePlan2.setTheater(theater);

            MoviePlan moviePlan3 = new MoviePlan();
            moviePlan3.setDate(LocalDate.now().plusDays(2));
            moviePlan3.setShowNumber(ShowNumber.NOON);
            moviePlan3.setMovie(movie);
            moviePlan3.setTheater(theater);

            // Gem i databasen
            iMoviePlanRepository.saveAll(List.of(moviePlan1, moviePlan2, moviePlan3));

            System.out.println("Init data: Movie plans added!");
        }
    }
}
