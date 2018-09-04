package com.example.demo.service;

import com.example.demo.controller.MovieController;
import com.example.demo.controller.TrailerController;
import com.example.demo.model.Movie;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class MovieAssembler implements ResourceAssembler<Movie, Resource<Movie>> {
    @Override
    public Resource<Movie> toResource(Movie movie) {
        return new Resource<>(movie,
                linkTo(methodOn(MovieController.class).getMovieById(movie.getMovieId())).withSelfRel(),
                linkTo(methodOn(TrailerController.class).findTrailer(movie.getMovieId())).withRel("trailer")
                );
    }
}
