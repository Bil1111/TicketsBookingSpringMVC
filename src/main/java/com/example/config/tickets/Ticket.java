package com.example.config.tickets;

import com.example.config.concerts.Concert;
import com.example.config.customers.Customer;
import jakarta.persistence.*;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name="tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name is required")
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    private String name;
    @Future(message = "Date must be in the future.")
    @NotEmpty(message = "Date is required")
    private LocalDate date;

    @NotEmpty(message = "Row is required")
    @Size(min = 1, max = 5, message = "Row must be between 1 and 5 characters")
    private String row;

    @NotEmpty(message = "Place is required")
    @Size(min = 1, max = 5, message = "Place must be between 1 and 5 characters")
    private String place;
    @NotEmpty(message = "Venue must not be empty")
    @Size(min = 3, max = 150, message = "Venue must be between 3 and 150 characters")
    private String venue;

    @NotEmpty(message = "Artist must not be empty")
    @Size(min = 3, max = 100, message = "Artist name must be between 3 and 100 characters")
    private String artist;

    @Size(min = 10, max = 500, message = "Description must be between 10 and 500 characters")
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "concert_id")
    @NotNull(message = "Concert is required")
    private Concert concert;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    @NotNull(message = "Customer is required")
    private Customer customer;

    public Ticket(String name, LocalDate date, String row, String place, String venue, String artist, String description, Concert concert, Customer customer) {
        this.name = name;
        this.date = date;
        this.row = row;
        this.place = place;
        this.venue = venue;
        this.artist = artist;
        this.description = description;
        this.concert = concert;
        this.customer = customer;
    }

    public Ticket() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Concert getConcert() {
        return concert;
    }

    public void setConcert(Concert concert) {
        this.concert = concert;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", row='" + row + '\'' +
                ", place='" + place + '\'' +
                ", concert=" + concert.getId() +
                ", customer=" + customer.getId() +
                '}';
    }
}
