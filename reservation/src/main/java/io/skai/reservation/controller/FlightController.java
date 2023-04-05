package io.skai.reservation.controller;

import io.skai.reservation.model.FlightModel;
import io.skai.reservation.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/flight")
public class FlightController {

    private final FlightService flightService;

    @PostMapping("/new")
    public ResponseEntity<FlightModel> save(@RequestBody FlightModel flight) {
        return ResponseEntity.ok(flightService.create(flight));
    }

    @GetMapping
    public ResponseEntity<List<FlightModel>> getAll() {
        return ResponseEntity.ok(flightService.getAllFlights());
    }

    @GetMapping("/{date}")
    public ResponseEntity<List<FlightModel>> getAllByDepartureDate(@PathVariable String date) {
        return ResponseEntity.ok(flightService.getAllFlightsByDepartureDate(date));
    }
}
