package com.example.demo.controller;


import com.example.demo.model.Movie;
import com.example.demo.service.MovieService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/movies")
    public List<Movie> getMovie(@RequestParam String movie) throws IOException {
        ObjectMapper mapper =  new ObjectMapper();
        return movieService.getMovies(movie).getSearch();
    }
}
