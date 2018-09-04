package com.example.demo.controller;

import com.example.demo.crud.ITrailerRepository;
import com.example.demo.model.Trailer;
import com.example.demo.service.TrailerAssembler;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trailers")
public class TrailerController {
    private final ITrailerRepository trailerRepository;
    private final TrailerAssembler assembler;

    public TrailerController(ITrailerRepository trailerRepository, TrailerAssembler assembler) {
        this.trailerRepository = trailerRepository;
        this.assembler = assembler;
    }

    @GetMapping("/{id}")
    public Resource<Trailer> findTrailer(@PathVariable Long id) {
        return assembler.toResource(this.trailerRepository.findByTrailerId(id));
    }
}
