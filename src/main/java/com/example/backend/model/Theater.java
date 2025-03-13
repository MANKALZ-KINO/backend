package com.example.backend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Theater {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long theaterId;

    private int capacity;

    private String theaterName;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cinema_id", referencedColumnName = "cinemaId", nullable = false)
    private Cinema cinema;

    @JsonManagedReference
    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL)
    private List<Seat> seats;

    public Theater(long theaterId, int capacity, String theaterName, Cinema cinema, List<Seat> seats) {
        this.theaterId = theaterId;
        this.capacity = capacity;
        this.theaterName = theaterName;
        this.cinema = cinema;
        this.seats = seats;
    }

    public Theater() {

    }

    public long getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(long theaterId) {
        this.theaterId = theaterId;
    }

    public String getTheaterName() {
        return theaterName;
    }

    public void setTheaterName(String theaterName) {
        this.theaterName = theaterName;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
