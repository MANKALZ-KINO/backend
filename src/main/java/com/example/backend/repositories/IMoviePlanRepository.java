package com.example.backend.repositories;

import com.example.backend.model.MoviePlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IMoviePlanRepository extends JpaRepository<MoviePlan,Long> {

    @Query("SELECT m.moviePlanDate FROM MoviePlan m")
    List<LocalDate> findAllMoviePlanDates();


    @Query("SELECT m FROM MoviePlan m WHERE m.moviePlanDate = :date")
    List<MoviePlan> findByMoviePlanDate(@Param("date") LocalDate date);



}
