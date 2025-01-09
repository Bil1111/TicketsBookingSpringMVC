package com.example.config.tickets;

import com.example.config.concerts.Concert;
import com.example.config.concerts.ConcertsRequest;
import com.example.config.customers.Customer;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class TicketsService {
    @Autowired
    private TicketsRepository ticketsRepository;

    public void addTicket(TicketsRequest request, Concert concert, Customer customer) {
        Ticket ticket = new Ticket(
                concert.getName(),
                concert.getDate(),
                request.getRow(),
                request.getPlace(),
                concert.getVenue(),
                concert.getArtist(),
                concert.getDescription(),
                concert,
                customer
        );
        ticketsRepository.save(ticket);
    }
    public void deleteTicket(Long id){
        ticketsRepository.deleteById(id);
    }
    public Optional<Ticket> getTicketById(Long id){
      return ticketsRepository.findById(id);
    }
    public List<Ticket> getAllTickets(){
        return ticketsRepository.findAll();
    }

}
