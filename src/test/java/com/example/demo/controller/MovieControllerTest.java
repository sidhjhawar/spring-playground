package com.example.demo.controller;

import com.example.demo.config.MovieConfig;
import com.example.demo.model.Movie;
import com.example.demo.model.MovieWrapper;
import com.example.demo.service.MovieService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class MovieControllerTest {


    private MovieService movieService;
    private MockRestServiceServer mockRestServiceServer;

    @Before
    public void setUp() {
        MovieConfig movieConfig =  Mockito.mock(MovieConfig.class);
        Mockito.when(movieConfig.getUrl()).thenReturn("http://foo.bar");
        Mockito.when(movieConfig.getApikey()).thenReturn("abc1234");

        this.movieService = new MovieService(movieConfig);
        this.mockRestServiceServer = MockRestServiceServer.createServer(movieService.getRestTemplate());

    }

    private final String jsonResponse = "{\n" +
            "   \"Search\":[\n" +
            "      {\n" +
            "         \"Title\":\"Harry Potter and the Deathly Hallows: Part 2\",\n" +
            "         \"Year\":\"2011\",\n" +
            "         \"imdbID\":\"tt1201607\",\n" +
            "         \"Type\":\"movie\",\n" +
            "         \"Poster\":\"https://m.media-amazon.com/images/M/MV5BMjIyZGU4YzUtNDkzYi00ZDRhLTljYzctYTMxMDQ4M2E0Y2YxXkEyXkFqcGdeQXVyNTIzOTk5ODM@._V1_SX300.jpg\"\n" +
            "      },\n" +
            "      {\n" +
            "         \"Title\":\"Harry Potter and the Sorcerer's Stone\",\n" +
            "         \"Year\":\"2001\",\n" +
            "         \"imdbID\":\"tt0241527\",\n" +
            "         \"Type\":\"movie\",\n" +
            "         \"Poster\":\"https://m.media-amazon.com/images/M/MV5BNjQ3NWNlNmQtMTE5ZS00MDdmLTlkZjUtZTBlM2UxMGFiMTU3XkEyXkFqcGdeQXVyNjUwNzk3NDc@._V1_SX300.jpg\"\n" +
            "      },\n" +
            "      {\n" +
            "         \"Title\":\"Harry Potter and the Chamber of Secrets\",\n" +
            "         \"Year\":\"2002\",\n" +
            "         \"imdbID\":\"tt0295297\",\n" +
            "         \"Type\":\"movie\",\n" +
            "         \"Poster\":\"https://m.media-amazon.com/images/M/MV5BMTcxODgwMDkxNV5BMl5BanBnXkFtZTYwMDk2MDg3._V1_SX300.jpg\"\n" +
            "      },\n" +
            "      {\n" +
            "         \"Title\":\"Harry Potter and the Goblet of Fire\",\n" +
            "         \"Year\":\"2005\",\n" +
            "         \"imdbID\":\"tt0330373\",\n" +
            "         \"Type\":\"movie\",\n" +
            "         \"Poster\":\"https://m.media-amazon.com/images/M/MV5BMTI1NDMyMjExOF5BMl5BanBnXkFtZTcwOTc4MjQzMQ@@._V1_SX300.jpg\"\n" +
            "      },\n" +
            "      {\n" +
            "         \"Title\":\"Harry Potter and the Prisoner of Azkaban\",\n" +
            "         \"Year\":\"2004\",\n" +
            "         \"imdbID\":\"tt0304141\",\n" +
            "         \"Type\":\"movie\",\n" +
            "         \"Poster\":\"https://m.media-amazon.com/images/M/MV5BMTY4NTIwODg0N15BMl5BanBnXkFtZTcwOTc0MjEzMw@@._V1_SX300.jpg\"\n" +
            "      },\n" +
            "      {\n" +
            "         \"Title\":\"Harry Potter and the Order of the Phoenix\",\n" +
            "         \"Year\":\"2007\",\n" +
            "         \"imdbID\":\"tt0373889\",\n" +
            "         \"Type\":\"movie\",\n" +
            "         \"Poster\":\"https://m.media-amazon.com/images/M/MV5BMTM0NTczMTUzOV5BMl5BanBnXkFtZTYwMzIxNTg3._V1_SX300.jpg\"\n" +
            "      },\n" +
            "      {\n" +
            "         \"Title\":\"Harry Potter and the Deathly Hallows: Part 1\",\n" +
            "         \"Year\":\"2010\",\n" +
            "         \"imdbID\":\"tt0926084\",\n" +
            "         \"Type\":\"movie\",\n" +
            "         \"Poster\":\"https://m.media-amazon.com/images/M/MV5BMTQ2OTE1Mjk0N15BMl5BanBnXkFtZTcwODE3MDAwNA@@._V1_SX300.jpg\"\n" +
            "      },\n" +
            "      {\n" +
            "         \"Title\":\"Harry Potter and the Half-Blood Prince\",\n" +
            "         \"Year\":\"2009\",\n" +
            "         \"imdbID\":\"tt0417741\",\n" +
            "         \"Type\":\"movie\",\n" +
            "         \"Poster\":\"https://m.media-amazon.com/images/M/MV5BNzU3NDg4NTAyNV5BMl5BanBnXkFtZTcwOTg2ODg1Mg@@._V1_SX300.jpg\"\n" +
            "      },\n" +
            "      {\n" +
            "         \"Title\":\"When Harry Met Sally...\",\n" +
            "         \"Year\":\"1989\",\n" +
            "         \"imdbID\":\"tt0098635\",\n" +
            "         \"Type\":\"movie\",\n" +
            "         \"Poster\":\"https://m.media-amazon.com/images/M/MV5BMjE0ODEwNjM2NF5BMl5BanBnXkFtZTcwMjU2Mzg3NA@@._V1_SX300.jpg\"\n" +
            "      },\n" +
            "      {\n" +
            "         \"Title\":\"Dirty Harry\",\n" +
            "         \"Year\":\"1971\",\n" +
            "         \"imdbID\":\"tt0066999\",\n" +
            "         \"Type\":\"movie\",\n" +
            "         \"Poster\":\"https://m.media-amazon.com/images/M/MV5BMzdhMTM2YTItOWU2YS00MTM0LTgyNDYtMDM1OWM3NzkzNTM2XkEyXkFqcGdeQXVyNjc1NTYyMjg@._V1_SX300.jpg\"\n" +
            "      }\n" +
            "   ],\n" +
            "   \"totalResults\":\"590\",\n" +
            "   \"Response\":\"True\"\n" +
            "}";

    @Test
    public void getMovie() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        MovieWrapper movies = objectMapper.readValue(jsonResponse, MovieWrapper.class);
        String jsonExpected = objectMapper.writeValueAsString(movies.getSearch());

        mockRestServiceServer
                .expect(MockRestRequestMatchers.requestTo("http://foo.bar/?s=harry&apikey=abc1234"))
                .andExpect(MockRestRequestMatchers.method(HttpMethod.GET))
                .andRespond(MockRestResponseCreators.withSuccess(jsonResponse, MediaType.APPLICATION_JSON));

        String jsonActual = objectMapper.writeValueAsString(movieService.getMovies("harry").getSearch());
        assertThat(jsonActual, IsEqual.equalTo(jsonExpected));
        mockRestServiceServer.verify();
    }
}