package com.example.backend.controllers;

import com.example.backend.model.MoviePlan;
import com.example.backend.model.Theater;
import com.example.backend.repositories.ITheaterRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/theater")
public class TheaterController {

    private final ITheaterRepository theaterRepository;


    public TheaterController(ITheaterRepository theaterRepository) {
        this.theaterRepository = theaterRepository;
    }

    // GET: Hent alle
    @GetMapping("/all")
    public List<Theater> getAllTheaters() {
        return theaterRepository.findAll();
    }

    //GET
    @GetMapping("/movieplans/{id}")
    public List<Theater> theaterById(@PathVariable Long id) {
        List<Theater> moviePlanForMovie = new ArrayList<>();
        for (Theater t : theaterRepository.findAll())
            if (t.getTheaterId() == id){
                moviePlanForMovie.add(t);
            }
        return moviePlanForMovie;
    }




}
