package com.example.backend.config;
import com.example.backend.model.Cinema;
import com.example.backend.model.Employee;
import com.example.backend.model.Role;
import com.example.backend.repositories.ICinemaRepository;
import com.example.backend.repositories.IEmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeInitData implements CommandLineRunner {

    private final IEmployeeRepository iEmployeeRepository;
    private final ICinemaRepository iCinemaRepository;

    public EmployeeInitData(IEmployeeRepository iEmployeeRepository, ICinemaRepository iCinemaRepository) {
        this.iEmployeeRepository = iEmployeeRepository;
        this.iCinemaRepository = iCinemaRepository;
    }


    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // Tjek om der allerede findes ansatte
        if (iEmployeeRepository.count() == 0) {
            // Find eller opret en Cinema
            Cinema cinema = iCinemaRepository.findById(1L).orElse(null);

            if (cinema == null) {
                cinema = new Cinema();
                cinema.setName("Copenhagen Grand Cinema");
                cinema.setAddress("Main Street 123, Copenhagen");
                cinema = iCinemaRepository.saveAndFlush(cinema); // Sikrer at Hibernate håndterer entiteten korrekt
            }


            // Opret ansatte
            Employee employee1 = new Employee();
            employee1.setName("John");
            employee1.setPassword("password1");
            employee1.setRole(Role.ADMIN);
            employee1.setCinema(cinema);

            Employee employee2 = new Employee();
            employee2.setName("Jane");
            employee2.setPassword("password2");
            employee2.setRole(Role.SALES);
            employee2.setCinema(cinema);

            Employee employee3 = new Employee();
            employee3.setName("Mark Johnson");
            employee3.setPassword("password3");
            employee3.setRole(Role.CLEANER);
            employee3.setCinema(cinema);

            // Gem de ansatte i databasen
            iEmployeeRepository.saveAll(List.of(employee1, employee2, employee3));

            System.out.println("Init data: Employees added");
        }
    }
}
