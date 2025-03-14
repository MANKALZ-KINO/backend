package com.example.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Cinema {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)

    private Long cinemaId;

    private String name;
    private String address;
    //Jeg er den passive part – kig på region i Kommune for at finde relationen
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cinema")
    @JsonBackReference
    private Set<Employee> employees = new HashSet<>();

    public Long getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(Long cinemaId) {
        this.cinemaId = cinemaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
