package com.example.backend.config;

import com.example.backend.model.Cinema;
import com.example.backend.model.Theater;
import com.example.backend.repositories.ICinemaRepository;
import com.example.backend.repositories.ITheaterRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TheaterInitData implements CommandLineRunner {

    private final ITheaterRepository iTheaterRepository;
    private final ICinemaRepository iCinemaRepository;

    public TheaterInitData(ITheaterRepository iTheaterRepository, ICinemaRepository iCinemaRepository) {
        this.iTheaterRepository = iTheaterRepository;
        this.iCinemaRepository = iCinemaRepository;
    }

    @Override
    @Transactional // Sikrer at alt kører i en transaktion
    public void run(String... args) throws Exception {
        if (iTheaterRepository.count() == 0) { // Undgå dubletter

            Cinema cinema = iCinemaRepository.findById(1L).orElseGet(() -> {
                Cinema newCinema = new Cinema();
                newCinema.setName("Nordisk Film Biografer");
                newCinema.setAddress("Gammel Kongevej 20, Frederiksberg");
                return iCinemaRepository.save(newCinema); // Gemmer
            });


            Theater smallTheater = new Theater();
            smallTheater.setTheaterName("Small Theater");
            smallTheater.setCapacity(3);
            smallTheater.setCinema(cinema);

            Theater largeTheater = new Theater();
            largeTheater.setTheaterName("Large Theater");
            largeTheater.setCapacity(4);
            largeTheater.setCinema(cinema);

            iTheaterRepository.saveAll(List.of(smallTheater, largeTheater));
        }
    }
}
