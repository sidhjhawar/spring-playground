package com.example.demo;

import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(FlightController.class)
public class FlightControllerTest {

    @Autowired
    MockMvc mvc;

    @Test
    public void flightTest() throws Exception {
        this.mvc.perform(
                MockMvcRequestBuilders.get("/flights/flight")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.Tickets[0].['Passenger'].FirstName", Is.is("John")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.Tickets[0].['Passenger'].LastName", Is.is("Doe")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.Tickets[0].Price", Is.is("1000")));
    }

    @Test
    public void flightsTest() throws Exception {
        this.mvc.perform(
                MockMvcRequestBuilders.get("/flights")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].Tickets[0].['Passenger'].FirstName", Is.is("John")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].Tickets[0].['Passenger'].LastName", Is.is("Doe")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].Tickets[0].Price", Is.is("1000")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].Tickets[0].['Passenger'].FirstName", Is.is("Jane")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].Tickets[0].['Passenger'].LastName", Is.is("Doe")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].Tickets[0].Price", Is.is("2000")));
    }
}