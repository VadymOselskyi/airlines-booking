package io.skai.reservation.controller;

import io.skai.reservation.dto.AirportDto;
import io.skai.reservation.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airports")
@RequiredArgsConstructor
public class AirportController {

    private final AirportService airportService;

    @PostMapping
    public ResponseEntity<AirportDto> save(@RequestBody AirportDto airport) {
        return ResponseEntity.ok(airportService.create(airport));
    }

    @GetMapping
    public ResponseEntity<List<AirportDto>> getAll() {
        return ResponseEntity.ok(airportService.getAllAirports());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AirportDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(airportService.getAirport(id));
    }
}