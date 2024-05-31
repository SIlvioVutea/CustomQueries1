package com.example.demo.flights.controllers;

import com.example.demo.flights.models.Flight;
import com.example.demo.flights.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/v1/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @PostMapping
    public Collection<Flight> create() {
        return flightService.create();
    }

    @GetMapping
    public Page<Flight> loadAll(@RequestParam int page, @RequestParam int size) {
        return flightService.getAll(page, size);
    }
}
