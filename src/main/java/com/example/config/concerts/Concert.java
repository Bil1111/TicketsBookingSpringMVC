package com.example.config.concerts;

import com.example.config.tickets.Ticket;
import jakarta.persistence.*;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="concerts")
public class Concert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name must not be empty")
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    private String name;

    @Future(message = "Date must be in the future.")
    private LocalDate date;

    @NotEmpty(message = "Venue must not be empty")
    @Size(min = 3, max = 150, message = "Venue must be between 3 and 150 characters")
    private String venue;

    @NotEmpty(message = "Artist must not be empty")
    @Size(min = 3, max = 100, message = "Artist name must be between 3 and 100 characters")
    private String artist;

    @Size(min = 10, max = 500, message = "Description must be between 10 and 500 characters")
    private String description;

    @OneToMany(mappedBy = "concert", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Ticket> tickets;

    public Concert() {
    }

    public Concert(String name, LocalDate date, String venue, String artist, String description) {
        this.name = name;
        this.date = date;
        this.venue = venue;
        this.artist = artist;
        this.description = description;
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

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
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
        return "Concert{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", venue='" + venue + '\'' +
                ", artist='" + artist + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
