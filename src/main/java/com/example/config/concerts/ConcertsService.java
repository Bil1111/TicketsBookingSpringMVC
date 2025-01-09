package com.example.config.concerts;

import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ConcertsService {
    @Autowired
    ConcertsRepository concertsRepository;

    public void addConcert(ConcertsRequest request) {
        Concert concert = new Concert(
                request.getName(),
                request.getDate(),
                request.getVenue(),
                request.getArtist(),
                request.getDescription());
        concertsRepository.save(concert);
    }

    public void deleteConcert(Long id) {
        concertsRepository.deleteById(id);
    }

    public void updateConcertDetails(ConcertsRequest request, Long concertId) {
        Optional<Concert> concertOptional = concertsRepository.findById(concertId);

        concertOptional.ifPresentOrElse(concert -> {
            if (StringUtils.isNotBlank(request.getName())) {
                concert.setName(request.getName());
            }
            if (StringUtils.isNotBlank(request.getVenue())) {
                concert.setVenue(request.getVenue());
            }
            if (request.getDate() != null) {
                concert.setDate(request.getDate());
            }
            if (StringUtils.isNotBlank(request.getArtist())) {
                concert.setArtist(request.getArtist());
            }
            if (StringUtils.isNotBlank(request.getDescription())) {
                concert.setDescription(request.getDescription());
            }
            concertsRepository.save(concert);
        }, () -> {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Concert with id " + concertId + " not found"
            );
        });
    }

    public Optional<Concert> getConcertById(Long id) {
        return concertsRepository.findById(id);
    }

    public List<Concert> getAllConcerts() {
        return concertsRepository.findAll();
    }
}
