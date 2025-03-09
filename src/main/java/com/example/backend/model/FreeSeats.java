package com.example.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import java.time.LocalDate;

@Entity
@Immutable
@Subselect("SELECT * FROM freeseats3") // Kun SELECT her!
public class FreeSeats {
    @Id
    private Long seatId;
    private int rowNum;
    private int seatNumb;
    private Long theaterId;
    private Long movieId;
    @Enumerated(EnumType.STRING)
    private ShowNumber showNumber;
    private LocalDate moviePlanDate;
    private Long moviePlanId;


    public Long getSeatId() {
        return seatId;
    }

    public void setSeatId(Long seatId) {
        this.seatId = seatId;
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public int getSeatNumb() {
        return seatNumb;
    }

    public void setSeatNumb(int seatNumb) {
        this.seatNumb = seatNumb;
    }

    public Long getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(Long theaterId) {
        this.theaterId = theaterId;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public ShowNumber getShowNumber() {
        return showNumber;
    }

    public void setShowNumber(ShowNumber showNumber) {
        this.showNumber = showNumber;
    }

    public LocalDate getMoviePlanDate() {
        return moviePlanDate;
    }

    public void setMoviePlanDate(LocalDate moviePlanDate) {
        this.moviePlanDate = moviePlanDate;
    }

    public Long getMoviePlanId() {
        return moviePlanId;
    }

    public void setMoviePlanId(Long moviePlanId) {
        this.moviePlanId = moviePlanId;
    }
}

