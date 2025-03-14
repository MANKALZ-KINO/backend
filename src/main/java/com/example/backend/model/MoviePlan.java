package com.example.backend.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "moviePlanId")

@Entity
public class MoviePlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long moviePlanId;
    private LocalDate moviePlanDate;


   @Enumerated(EnumType.STRING)
    @Column(name = "show_number", nullable = false)
    private ShowNumber showNumber;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "movie_id", referencedColumnName = "movieId", nullable = false)
    //@JsonIgnore
    private Movie movie;


    @ManyToOne
    @JoinColumn(name = "theater_id", referencedColumnName = "theaterId", nullable = true)
    private Theater theater;

    @OneToMany(mappedBy = "moviePlan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> tickets;

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Long getMoviePlanId() {
        return moviePlanId;
    }

    public void setMoviePlanId(Long moviePlanId) {
        this.moviePlanId = moviePlanId;
    }

    public LocalDate getMoviePlanDate() {
        return moviePlanDate;
    }

    public void setMoviePlanDate(LocalDate moviePlanDate) {
        this.moviePlanDate = moviePlanDate;
    }

    public ShowNumber getShowNumber() {
        return showNumber;
    }

    public void setShowNumber(ShowNumber showNumber) {
        this.showNumber = showNumber;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Theater getTheater() {
        return theater;
    }

    public void setTheater(Theater theater) {
        this.theater = theater;
    }
}
