package com.example.demo.service;


import com.example.demo.controller.TrailerController;
import com.example.demo.model.Trailer;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class TrailerAssembler implements ResourceAssembler<Trailer, Resource<Trailer>> {
    @Override
    public Resource<Trailer> toResource(Trailer trailer) {
        return new Resource<>(trailer,
                linkTo(methodOn(TrailerController.class).findTrailer(trailer.getTrailerId())).withRel("trailer"));
    }
}
