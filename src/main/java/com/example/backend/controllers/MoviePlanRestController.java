package com.example.backend.controllers;

import com.example.backend.model.MoviePlan;

import com.example.backend.repositories.FreeSeatsRepository;
import com.example.backend.service.MoviePlanService;

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


    public MoviePlanRestController(MoviePlanService moviePlanService, FreeSeatsRepository freeSeatsRepository) {
        this.moviePlanService = moviePlanService;
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
        return moviePlanForMovie;
    }
    @GetMapping("/movieplans")
    public List<MoviePlan> moviePlansByDate(@RequestParam("date") String movieplandate) {
        LocalDate date = LocalDate.parse(movieplandate);
        return moviePlanService.movieplansByDate(date);
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

