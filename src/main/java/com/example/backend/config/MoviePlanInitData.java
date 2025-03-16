package com.example.backend.config;

import com.example.backend.model.*;
import com.example.backend.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class MoviePlanInitData implements CommandLineRunner {


    private final IMoviePlanRepository iMoviePlanRepository;
    private final IMovieRepository iMovieRepository;
    private final ITheaterRepository iTheaterRepository;
    private final ICinemaRepository iCinemaRepository;

    public MoviePlanInitData(IMoviePlanRepository iMoviePlanRepository, IMovieRepository iMovieRepository, ITheaterRepository iTheaterRepository, ICinemaRepository iCinemaRepository) {
        this.iMoviePlanRepository = iMoviePlanRepository;
        this.iMovieRepository = iMovieRepository;
        this.iTheaterRepository = iTheaterRepository;
        this.iCinemaRepository = iCinemaRepository;
    }


    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // Undgå dubletter
        if (iMoviePlanRepository.count() == 0) {
            // Find eller opret en biograf (Cinema)
            Cinema cinema = iCinemaRepository.findById(1L).orElseGet(() -> {
                Cinema newCinema = new Cinema();
                newCinema.setName("City Cinema");  // Opretter en cinema
                return iCinemaRepository.save(newCinema);
            });

            // Find eller opret en film
            Movie movie = iMovieRepository.findById(1L).orElseThrow(() -> new RuntimeException("Movie not found"));
            Movie movie2 = iMovieRepository.findById(2L).orElseThrow(() -> new RuntimeException("Movie not found"));
            Movie movie3 = iMovieRepository.findById(3L).orElseThrow(() -> new RuntimeException("Movie not found"));

            // Find eller opret en biografsal (Theater)
            Theater theater = iTheaterRepository.findById(1L).orElseGet(() -> {
                Theater newTheater = new Theater();
                newTheater.setTheaterName("Grand Hall");
                newTheater.setCapacity(250);
                newTheater.setCinema(cinema);
                return iTheaterRepository.save(newTheater);
            });

            // Opret filmvisninger
            MoviePlan moviePlan1 = new MoviePlan();
            moviePlan1.setMoviePlanDate(LocalDate.now());
            moviePlan1.setShowNumber(ShowNumber.MORNING);
            moviePlan1.setMovie(movie);
            moviePlan1.setTheater(theater);

            MoviePlan moviePlan2 = new MoviePlan();
            moviePlan2.setMoviePlanDate(LocalDate.now().plusDays(1));
            moviePlan2.setShowNumber(ShowNumber.AFTERNOON);
            moviePlan2.setMovie(movie);
            moviePlan2.setTheater(theater);

            MoviePlan moviePlan3 = new MoviePlan();
            moviePlan3.setMoviePlanDate(LocalDate.now().plusDays(2));
            moviePlan3.setShowNumber(ShowNumber.MORNING);
            moviePlan3.setMovie(movie);
            moviePlan3.setTheater(theater);

            MoviePlan moviePlan4 = new MoviePlan();
            moviePlan4.setMoviePlanDate(LocalDate.now().plusDays(2));
            moviePlan4.setShowNumber(ShowNumber.NOON);
            moviePlan4.setMovie(movie2);
            moviePlan4.setTheater(theater);

            MoviePlan moviePlan5 = new MoviePlan();
            moviePlan5.setMoviePlanDate(LocalDate.now().plusDays(2));
            moviePlan5.setShowNumber(ShowNumber.AFTERNOON);
            moviePlan5.setMovie(movie3);
            moviePlan5.setTheater(theater);

            // Gem i databasen
            iMoviePlanRepository.saveAll(List.of(moviePlan1, moviePlan2, moviePlan3, moviePlan4, moviePlan5));

            System.out.println("Init data: Movie plans added!");
        }
    }
}
