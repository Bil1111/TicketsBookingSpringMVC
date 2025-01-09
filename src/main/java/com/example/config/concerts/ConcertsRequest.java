package com.example.config.concerts;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class ConcertsRequest {
    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    private String name;

    @Future(message = "Date is required.")
    private LocalDate date;

    @NotBlank(message = "Venue is required")
    @Size(min = 3, max = 150, message = "Venue must be between 3 and 150 characters")
    private String venue;

    @NotBlank(message = "Artist is required")
    @Size(min = 3, max = 100, message = "Artist name must be between 3 and 100 characters")
    private String artist;

    @NotBlank(message = "Description is required")
    @Size(min = 10, max = 500, message = "Description must be between 10 and 500 characters")
    private String description;

    public ConcertsRequest(String name, LocalDate date, String venue, String artist, String description) {
        this.name = name;
        this.date = date;
        this.venue = venue;
        this.artist = artist;
        this.description = description;
    }

    public ConcertsRequest() {
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
}
