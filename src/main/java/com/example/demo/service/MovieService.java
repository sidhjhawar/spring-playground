package com.example.demo.service;

import com.example.demo.config.MovieConfig;
import com.example.demo.model.Movie;
import com.example.demo.model.MovieWrapper;
import org.springframework.hateoas.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    private final RestTemplate restTemplate =  new RestTemplate();

    private MovieConfig movieConfig;

    public MovieService(MovieConfig movieConfig) {
        this.movieConfig = movieConfig;
    }

    public RestTemplate getRestTemplate() {
        return this.restTemplate;
    }

    public MovieWrapper getMovies(String titleString) throws IOException {
        URI uri =  new UriTemplate("{host}/?s={title}&apikey={apikey}").expand(movieConfig.getUrl(),titleString, movieConfig.getApikey());

        RequestEntity request = RequestEntity
                .get(uri)
                .accept(MediaType.APPLICATION_JSON)
                .build();

        ResponseEntity response = this.restTemplate.exchange(
                request,
                MovieWrapper.class
        );

        return (MovieWrapper) response.getBody();
    }

    public List<Resource<Movie>> getAllMovies(Iterable<Movie> all, MovieAssembler assembler) {
        List<Resource<Movie>> movieResource = new ArrayList<>();
        all.forEach(m -> {
            movieResource.add(assembler.toResource(m));
        });
        return  movieResource;
    }
}
