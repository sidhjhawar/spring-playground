package com.example.demo.controller;

import com.example.demo.controller.FlightController;
import com.example.demo.model.Flight;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;

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

    @Test
    public void flightsTotalsUsingStringTest() throws Exception {

        String json = "{\n" +
                "  \"Tickets\": [\n" +
                "    {\n" +
                "      \"Passenger\": {\n" +
                "        \"FirstName\": \"Some name\",\n" +
                "        \"LastName\": \"Some other name\"\n" +
                "      },\n" +
                "      \"Price\": 200\n" +
                "    },\n" +
                "    {\n" +
                "      \"Passenger\": {\n" +
                "        \"FirstName\": \"Name B\",\n" +
                "        \"LastName\": \"Name C\"\n" +
                "      },\n" +
                "      \"Price\": 150\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        this.mvc.perform(
                MockMvcRequestBuilders.post("/flights/tickets/total")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.result", Is.is(350)));
    }

    @Test
    public void flightsTotalsUsingJacksonTest() throws Exception {

        Flight britishAirways = new Flight();

        Flight.Person person = new Flight.Person();
        person.setFirstName("John");
        person.setLastName("Doe");
        Flight.Ticket ticket = new Flight.Ticket();
        ticket.setPrice("1000");
        ticket.setPassenger(person);
        britishAirways.setTickets(Arrays.asList(ticket));

        britishAirways.setDeparts(new Date());
        Flight.Person person1 = new Flight.Person();
        person1.setFirstName("Jane");
        person1.setLastName("Doe");
        Flight.Ticket ticket1 = new Flight.Ticket();
        ticket1.setPrice("2000");
        ticket1.setPassenger(person1);
        britishAirways.setTickets(Arrays.asList(ticket, ticket1));
        ObjectMapper objectMapper =  new ObjectMapper();
        String json = objectMapper.writeValueAsString(britishAirways);

        this.mvc.perform(
                MockMvcRequestBuilders.post("/flights/tickets/total")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.result", Is.is(3000)));
    }


    @Test
    public void flightsTotalsUsingFixturesTest() throws Exception {

        String json = getJSON("/data.json");

        this.mvc.perform(
                MockMvcRequestBuilders.post("/flights/tickets/total")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.result", Is.is(350)));
    }

    private String getJSON(String path) throws Exception {
        URL url = this.getClass().getResource(path);
        return new String(Files.readAllBytes(Paths.get(url.getFile())));
    }

}