package com.example.demo.controller;


import com.example.demo.model.Flight;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @GetMapping("")
    public List<Flight> flights() {
        List<Flight> flights = new ArrayList<>();
        Flight britishAirways = new Flight();

        Flight.Person person = new Flight.Person();
        person.setFirstName("John");
        person.setLastName("Doe");
        Flight.Ticket ticket = new Flight.Ticket();
        ticket.setPrice("5000");
        ticket.setPassenger(person);
        britishAirways.setTickets(Arrays.asList(ticket));

        britishAirways.setDeparts(new Date());
        flights.add(britishAirways);


        Flight lufthansa = new Flight();

        Flight.Person person1 = new Flight.Person();
        person1.setFirstName("Jane");
        person1.setLastName("Doe");
        Flight.Ticket ticket1 = new Flight.Ticket();
        ticket1.setPrice("2000");
        ticket1.setPassenger(person1);
        lufthansa.setDeparts(new Date());
        lufthansa.setTickets(Arrays.asList(ticket1));

        flights.add(lufthansa);

        return flights;
    }

    @GetMapping("/flight")
    public Flight flight() {
        Flight britishAirways = new Flight();

        Flight.Person person = new Flight.Person();
        person.setFirstName("John");
        person.setLastName("Doe");

        Flight.Ticket ticket = new Flight.Ticket();
        ticket.setPrice("1000");
        ticket.setPassenger(person);

        britishAirways.setDeparts(new Date());
        britishAirways.setTickets(Arrays.asList(ticket));

        return britishAirways;
    }

    @PostMapping("/tickets/total")
    public String calculateTotal(@RequestBody Flight flight) throws Exception {
        int ticketsCount = flight.getTickets().size();
        int sum = 0;
        ObjectMapper objectMapper = new ObjectMapper();
        for (int i = 0; i < ticketsCount; i++) {
            sum += Integer.parseInt(flight.getTickets().get(i).getPrice());
        }

        HashMap<String, Object> data = new HashMap<>();
        data.put("result", sum);
        return objectMapper.writeValueAsString(data);
    }


}
