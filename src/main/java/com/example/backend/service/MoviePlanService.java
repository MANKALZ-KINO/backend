package com.example.backend.service;

import com.example.backend.model.MoviePlan;
import com.example.backend.repositories.IMoviePlanRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MoviePlanService {

    private IMoviePlanRepository iMoviePlanRepository;


    public MoviePlanService(IMoviePlanRepository iMoviePlanRepository) {
        this.iMoviePlanRepository = iMoviePlanRepository;
    }

    public List<MoviePlan> moviePlansWithMovieId(Long id) {
        return iMoviePlanRepository.findAll();
    }
    public List<LocalDate> getAllMoviePlanLocalDates() {
        return iMoviePlanRepository.findAllMoviePlanDates();
    }
    public List<MoviePlan> movieplansByDate(LocalDate movieplandate) {
        return iMoviePlanRepository.findByMoviePlanDate(movieplandate);
    }



    public Optional<MoviePlan> movieplans(Long id) {
        return iMoviePlanRepository.findById(id);
    }

    public void deleteMovieById(Long id) {
        iMoviePlanRepository.deleteById(id);
    }

}
