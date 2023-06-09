package io.skai.reservation.controller;

import io.skai.reservation.dto.FlightDto;
import io.skai.reservation.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/flights")
public class FlightController {

    private final FlightService flightService;

    @PostMapping
    public ResponseEntity<FlightDto> save(@RequestBody FlightDto flightDto) {
        return ResponseEntity.ok(flightService.create(flightDto));
    }

    @GetMapping
    public ResponseEntity<List<FlightDto>> getAll() {
        return ResponseEntity.ok(flightService.getAllFlights());
    }
}