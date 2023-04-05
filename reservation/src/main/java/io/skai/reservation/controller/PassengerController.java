package io.skai.reservation.controller;


import io.skai.reservation.model.PassengerModel;
import io.skai.reservation.service.PassengerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/passenger")
@RequiredArgsConstructor
public class PassengerController {

    private final PassengerService passengerService;

    @PostMapping
    public ResponseEntity<PassengerModel> save(@RequestBody PassengerModel passenger) {
        return ResponseEntity.ok(passengerService.create(passenger));
    }

    @GetMapping("/{email}")
    public ResponseEntity<PassengerModel> getAll(@PathVariable String email) {
        return ResponseEntity.ok(passengerService.getPassengerByEmail(email));
    }

}
