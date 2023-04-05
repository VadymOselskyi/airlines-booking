package io.skai.reservation.service.impl;

import io.skai.reservation.model.AirportModel;
import io.skai.reservation.repository.AirportRepository;
import io.skai.reservation.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AirportServiceImpl implements AirportService {
    private final AirportRepository airportRepository;

    @Override
    public AirportModel create(AirportModel airport) {
        return airportRepository.insert(airport);
    }

    @Override
    public List<AirportModel> getAllAirports() {
        return airportRepository.selectAll();
    }

    @Override
    public AirportModel getAirport(Long id) {
        return airportRepository.selectOneById(id);
    }
}
