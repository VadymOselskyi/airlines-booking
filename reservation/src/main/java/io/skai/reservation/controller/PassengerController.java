package io.skai.reservation.controller;


import io.skai.reservation.dto.PassengerDto;
import io.skai.reservation.service.PassengerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/passengers")
@RequiredArgsConstructor
public class PassengerController {

    private final PassengerService passengerService;

    @PostMapping
    public ResponseEntity<PassengerDto> save(@RequestBody PassengerDto passenger) {
        return ResponseEntity.ok(passengerService.create(passenger));
    }

    @GetMapping
    public ResponseEntity<List<PassengerDto>> getAll() {
        return ResponseEntity.ok(passengerService.getPassengers());
    }
}