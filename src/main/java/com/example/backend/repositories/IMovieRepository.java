package com.example.backend.repositories;

import com.example.backend.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IMovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findAllBymovieName(String movieName);
}
