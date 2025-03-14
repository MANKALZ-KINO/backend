package com.example.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;
    private String genre;
    private String movieName;
    private int ageLimit;
    private int duration;
    private String imageUrl;


    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true) //orphanremoval betyder at hvis en MoviePlan fjernes fra moviePlans-listen i Movie, slettes den automatisk fra databasen
    @JsonIgnore
    private List<MoviePlan> moviePlans; //cascade betyder at alle ændringer på Movie også gælder for MoviePlan

    public List<MoviePlan> getMoviePlans() {
        return moviePlans;
    }

    public void setMoviePlans(List<MoviePlan> moviePlans) {
        this.moviePlans = moviePlans;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(int ageLimit) {
        this.ageLimit = ageLimit;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
