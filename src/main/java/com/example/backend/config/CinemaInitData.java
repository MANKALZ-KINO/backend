package com.example.backend.config;

import com.example.backend.model.Cinema;
import com.example.backend.repositories.ICinemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;



@Component
public class CinemaInitData implements CommandLineRunner {

    private final ICinemaRepository iCinemaRepository;

    public CinemaInitData(ICinemaRepository iCinemaRepository) {
        this.iCinemaRepository = iCinemaRepository;
    }


    @Override
    public void run(String... args) {
        if (iCinemaRepository.count() == 0) {
            Cinema cinema1 = new Cinema();
            cinema1.setName("Nordisk Film Biografer");
            cinema1.setAddress("Gammel Kongevej 20, Frederiksberg");

            iCinemaRepository.save(cinema1);

        }
    }
}
