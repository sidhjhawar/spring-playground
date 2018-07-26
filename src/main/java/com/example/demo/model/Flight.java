package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class Flight {
    @JsonProperty("Departs")
    private Date departs;

    @JsonProperty("Tickets")
    private List<Ticket> tickets;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm")
    public Date getDeparts() {
        return departs;
    }

    public void setDeparts(Date departs) {
        this.departs = departs;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public static class Ticket {
        private String price;
        private Person passenger;

        @JsonProperty("Price")
        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        @JsonProperty("Passenger")
        public Person getPassenger() {
            return passenger;
        }

        public void setPassenger(Person passenger) {
            this.passenger = passenger;
        }
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Person {
        @JsonProperty("FirstName")
        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        @JsonProperty("LastName")
        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        private String firstName;
        private String lastName;
    }
}
