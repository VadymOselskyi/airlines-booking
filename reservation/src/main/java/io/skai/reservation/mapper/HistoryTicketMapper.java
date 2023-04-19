package io.skai.reservation.mapper;

import io.skai.reservation.dto.HistoryTicketDto;
import io.skai.reservation.jooq.tables.pojos.Airport;
import io.skai.reservation.jooq.tables.pojos.Flight;
import io.skai.reservation.jooq.tables.pojos.Ticket;
import io.skai.reservation.model.Passenger;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface HistoryTicketMapper {

    @Mapping(target = "passenger.id", ignore = true)
    @Mapping(target = "ticket.id", ignore = true)
    @Mapping(target = "flight.id", ignore = true)
    @Mapping(target = "departureAirport.id", ignore = true)
    @Mapping(target = "arrivalAirport.id", ignore = true)
    @Mapping(source = "passenger.firstName", target = "firstName")
    @Mapping(source = "passenger.lastName", target = "lastName")
    @Mapping(source = "passenger.email", target = "email")
    @Mapping(source = "passenger.phone", target = "phone")
    @Mapping(source = "ticket.seatNumber", target = "seatNumber")
    @Mapping(source = "ticket.price", target = "price")
    @Mapping(source = "flight.departureDate", target = "departureDate")
    @Mapping(source = "departureAirport.name", target = "departureAirportName")
    @Mapping(source = "departureAirport.countryCode", target = "departureCountryCode")
    @Mapping(source = "departureAirport.city", target = "departureCity")
    @Mapping(source = "flight.arrivalDate", target = "arrivalDate")
    @Mapping(source = "arrivalAirport.name", target = "arrivalAirportName")
    @Mapping(source = "arrivalAirport.countryCode", target = "arrivalCountryCode")
    @Mapping(source = "arrivalAirport.city", target = "arrivalCity")
    HistoryTicketDto mapToDto(Airport departureAirport, Airport arrivalAirport, Flight flight, Passenger passenger, Ticket ticket);
}