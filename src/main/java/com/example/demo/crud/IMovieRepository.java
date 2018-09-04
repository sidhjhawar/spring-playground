package com.example.demo.crud;

import com.example.demo.model.Movie;
import org.springframework.data.repository.CrudRepository;

public interface IMovieRepository extends CrudRepository<Movie, Long> {
    Iterable<Movie> findAll();
    Movie findByMovieId(Long id);
}
