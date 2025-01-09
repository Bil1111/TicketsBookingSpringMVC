package com.example.config.tickets;

import com.example.config.concerts.Concert;
import com.example.config.concerts.ConcertsRepository;
import com.example.config.customers.Customer;
import com.example.config.customers.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tickets")
public class TicketsController {
    @Autowired
    private TicketsService ticketsService;
    @Autowired
    private ConcertsRepository concertsRepository;
    @Autowired
    private CustomersRepository customersRepository;

    @PostMapping("/add")
    public ResponseEntity<String> addTicket(@RequestBody TicketsRequest request) {
        Concert concert = concertsRepository.findById(request.getConcert())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Concert not found"));

        Customer customer = customersRepository.findById(request.getCustomer())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));

        ticketsService.addTicket(request, concert, customer);

        return new ResponseEntity<>("Ticket added successfully", HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable("id") Long id) {
        Optional<Ticket> ticket = ticketsService.getTicketById(id);
        return ticket.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Ticket>> getAllTickets() {
        List<Ticket> tickets = ticketsService.getAllTickets();
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> returnTicket(@PathVariable("id") Long id) {
        ticketsService.deleteTicket(id);
        return new ResponseEntity<>("Ticket deleted successfully", HttpStatus.OK);
    }
}
