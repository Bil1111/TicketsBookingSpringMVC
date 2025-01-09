package com.example.config.concerts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/concerts")
public class ConcertsController {
    @Autowired
    private ConcertsService concertsService;


    @PostMapping("/add")
    public ResponseEntity<String> addConcert(@RequestBody ConcertsRequest request) {
        concertsService.addConcert(request);

        return new ResponseEntity<>("Concert added successfully", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Concert> getConcertById(@PathVariable("id") Long id) {
        Optional<Concert> concert = concertsService.getConcertById(id);
        return concert.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Concert>> getAllConcerts() {
        List<Concert> animals = concertsService.getAllConcerts();
        return new ResponseEntity<>(animals, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteConcert(@PathVariable("id") Long id) {
            concertsService.deleteConcert(id);
            return new ResponseEntity<>("Concert deleted successfully", HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateConcertDetails(@RequestBody ConcertsRequest request, @PathVariable("id") Long id) {
        concertsService.updateConcertDetails(request,id);
        return new ResponseEntity<>("Concert updated successfully", HttpStatus.OK);
    }

}
