package com.example.backend.config;

import com.example.backend.model.Movie;
import com.example.backend.repositories.IMovieRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MovieInitData implements CommandLineRunner {


    private final IMovieRepository iMovieRepository;

    public MovieInitData(IMovieRepository iMovieRepository) {
        this.iMovieRepository = iMovieRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (iMovieRepository.count() == 0) { // Undgå at indsætte dubletter

            Movie movie1 = new Movie();
            movie1.setMovieName("Home Alone");
            movie1.setGenre("Sci-Fi");
            movie1.setAgeLimit(13);
            movie1.setDuration(148);
            movie1.setImageUrl("https://m.media-amazon.com/images/M/MV5BNzNmNmQ2ZDEtMTc1MS00NjNiLThlMGUtZmQxNTg1Nzg5NWMzXkEyXkFqcGc@._V1_FMjpg_UX1000_.jpg");

            Movie movie2 = new Movie();
            movie2.setMovieName("The Dark Knight");
            movie2.setGenre("Action");
            movie2.setAgeLimit(12);
            movie2.setDuration(152);
            movie2.setImageUrl("https://m.media-amazon.com/images/M/MV5BMTMxNTMwODM0NF5BMl5BanBnXkFtZTcwODAyMTk2Mw@@._V1_FMjpg_UX1000_.jpg");

            Movie movie3 = new Movie();
            movie3.setMovieName("Titanic");
            movie3.setGenre("Romance");
            movie3.setAgeLimit(12);
            movie3.setDuration(195);
            movie3.setImageUrl("https://m.media-amazon.com/images/M/MV5BYzYyN2FiZmUtYWYzMy00MzViLWJkZTMtOGY1ZjgzNWMwN2YxXkEyXkFqcGc@._V1_.jpg");


            iMovieRepository.saveAll(List.of(movie1, movie2, movie3));
            iMovieRepository.flush(); // Sikrer at alle film bliver gemt korrekt

            System.out.println("Init data: 3 movies added!");
        }
    }
}
