package com.example.backend.controllers;

import com.example.backend.model.Movie;
import com.example.backend.model.MoviePlan;

import com.example.backend.model.Theater;
import com.example.backend.repositories.FreeSeatsRepository;
import com.example.backend.service.MoviePlanService;

import com.example.backend.service.MovieService;
import com.example.backend.service.TheaterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class MoviePlanRestController {


    private final MoviePlanService moviePlanService;
    private final MovieService movieService;
    private final TheaterService theaterService;


    public MoviePlanRestController(MoviePlanService moviePlanService, MovieService movieService, TheaterService theaterService) {
        this.moviePlanService = moviePlanService;
        this.movieService = movieService;
        this.theaterService = theaterService;
    }


    @GetMapping("/dates")
    public List<LocalDate> getFreeDates() {
        List<LocalDate> alldates = moviePlanService.getAllMoviePlanLocalDates();
        return alldates;
    }


    //GET
    @GetMapping("/movieplans/{id}")
    public List<MoviePlan> moviePlansWithMovieId(@PathVariable Long id) {
        List<MoviePlan> moviePlanForMovie = new ArrayList<>();
        for (MoviePlan m : moviePlanService.moviePlansWithMovieId(id))
            if (m.getMovie().getMovieId().equals(id)) {
                moviePlanForMovie.add(m);
            }
        System.out.println(moviePlanForMovie);
        return moviePlanForMovie;
    }
    @GetMapping("/movieplans")
    public List<MoviePlan> moviePlansByDate(@RequestParam("date") String movieplandate) {
        LocalDate date = LocalDate.parse(movieplandate);
        List<MoviePlan> plans = moviePlanService.movieplansByDate(date);
        System.out.println("Requested Date: " + date);
        System.out.println("Found Movie Plans: " + plans);
        return moviePlanService.movieplansByDate(date);
    }


    @GetMapping("/movieplans")
    public List<MoviePlan> moviePlans() {
        return moviePlanService.findAllMoviePlans();
    }

//    @GetMapping("/byDate/{movieplandate}")
//    public List<MoviePlan> moviePlansByDate(@PathVariable LocalDate movieplandate) {
//        List<MoviePlan> movieplansByDate = moviePlanService.movieplansByDate(movieplandate);
//        return movieplansByDate;
//    }

    @RequestMapping(
            value = "/movieplans",
            method = RequestMethod.POST,
            consumes = "text/plain"
    )
    public void moviePlansWithMovieId(@RequestBody String body) {
        System.out.println(body);
    }


    @PostMapping("/createMoviePlan")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> postMoviePlan(@RequestBody MoviePlan moviePlan) {
        try {
            System.out.println("Inserting new movie plan!");
            System.out.println(moviePlan);

            Movie movie = movieService.findMovieById(moviePlan.getMovie().getMovieId())
                    .orElseThrow(() -> new RuntimeException("Movie not found"));
            Theater theater = theaterService.findTheaterByIdNotList(moviePlan.getTheater().getTheaterId())
                    .orElseThrow(() -> new RuntimeException("Theater not found"));

            moviePlan.setMovie(movie);
            moviePlan.setTheater(theater);

            moviePlanService.saveMoviePlan(moviePlan);
            return ResponseEntity.ok("Movie plan created successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }


    //DELETE
    @DeleteMapping("/movieplan/{id}")
    public ResponseEntity<String> deleteMoviePlan(@PathVariable Long id) {
        Optional<MoviePlan> orgMoviePlan = moviePlanService.movieplans(id);
        if (orgMoviePlan.isPresent()) {
            moviePlanService.deleteMovieById(id);
            return ResponseEntity.ok("movieplan deleted");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("movieplan not found and could not be deleted");
        }
    }


}

