package com.example.demo.service;

import com.example.demo.model.MovieWrapper;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import java.io.IOException;
import java.net.URI;

@Service
public class MovieService {

    private final RestTemplate restTemplate =  new RestTemplate();

    public MovieWrapper getMovies(String titleString) throws IOException {
        URI uri =  new UriTemplate("http://www.omdbapi.com/?s={title}&apikey=ed6b8b87").expand(titleString);

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
}
