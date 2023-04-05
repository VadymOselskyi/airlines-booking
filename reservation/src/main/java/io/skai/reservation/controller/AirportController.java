package io.skai.reservation.controller;

import io.skai.reservation.model.AirportModel;
import io.skai.reservation.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airport")
@RequiredArgsConstructor
public class AirportController {

    private final AirportService airportService;

    @PostMapping
    public ResponseEntity<AirportModel> save(@RequestBody AirportModel airport) {
        return ResponseEntity.ok(airportService.create(airport));
    }

    @GetMapping
    public ResponseEntity<List<AirportModel>> getAll() {
        return ResponseEntity.ok(airportService.getAllAirports());
    }
}

