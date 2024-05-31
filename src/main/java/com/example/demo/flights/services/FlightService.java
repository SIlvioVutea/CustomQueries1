package com.example.demo.flights.services;

import com.example.demo.flights.enums.Status;
import com.example.demo.flights.models.Flight;
import com.example.demo.flights.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    private String generateRandomString(int length) {
        Random random = new Random();
        return random.ints(65, 91) // A-Z ASCII range
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public Collection<Flight> create() {
        List<Flight> flights = IntStream.range(0, 50)
                .mapToObj(i -> new Flight(
                        generateRandomString(10),
                        generateRandomString(3),
                        generateRandomString(3)
                ))
                .collect(Collectors.toList());

        return flightRepository.saveAll(flights);
    }

    public Page<Flight> getAll(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "fromAirport");
        Pageable pageable = PageRequest.of(page, size, sort);
        return flightRepository.findAll(pageable);
    }
}
