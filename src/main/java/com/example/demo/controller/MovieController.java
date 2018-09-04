package com.example.demo.controller;


import com.example.demo.crud.IMovieRepository;
import com.example.demo.model.Movie;
import com.example.demo.service.MovieAssembler;
import com.example.demo.service.MovieService;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final IMovieRepository repository;
    private final MovieAssembler assembler;
    private final MovieService service;

    public MovieController(IMovieRepository repository, MovieAssembler assembler, MovieService service) {
        this.repository = repository;
        this.assembler = assembler;
        this.service = service;
    }

    @GetMapping("")
    public List<Resource<Movie>> all() {
        return service.getAllMovies(this.repository.findAll(),assembler);
    }

    @GetMapping("/{id}")
    public Resource<Movie> getMovieById(@PathVariable Long id) {
        return assembler.toResource(this.repository.findByMovieId(id));
    }
}

