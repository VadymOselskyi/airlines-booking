package io.skai.reservation.service.impl;

import io.skai.reservation.dto.AirportDto;
import io.skai.reservation.jooq.tables.pojos.Airport;
import io.skai.reservation.mapper.AirportMapper;
import io.skai.reservation.repository.AirportRepository;
import io.skai.reservation.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AirportServiceImpl implements AirportService {

    private final AirportRepository airportRepository;
    private final AirportMapper airportMapper;

    @Override
    public AirportDto create(AirportDto airportDto) {

        Airport insertedAirport = airportRepository.insert(airportDto.name(), airportDto.countryCode(), airportDto.city());
        return airportMapper.airportToAirportDto(insertedAirport);
    }

    @Override
    @Cacheable(value = "airport")
    public List<AirportDto> getAllAirports() {
        return airportRepository.selectAll()
                .stream()
                .map(airportMapper::airportToAirportDto)
                .toList();
    }

    @Override
    @Cacheable(value = "airport")
    public AirportDto getAirport(Long id) {
        Airport airport = airportRepository.selectOne(id);
        return airportMapper.airportToAirportDto(airport);
    }
}