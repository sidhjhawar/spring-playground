package com.example.demo;


import com.example.demo.model.Flight;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @GetMapping("")
    public List<Flight> flights() {
        List<Flight> flights =  new ArrayList<>();
        Flight britishAirways = new Flight();

        Flight.Person person = new Flight.Person();
        person.setFirstName("John");
        person.setLastName("Doe");
        Flight.Ticket ticket = new Flight.Ticket();
        ticket.setPrice("1000");
        ticket.setPassenger(person);
        britishAirways.setTickets(Arrays.asList(ticket));

        britishAirways.setDeparts(new Date());
        flights.add(britishAirways);


        Flight lufthansa =  new Flight();

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


}
