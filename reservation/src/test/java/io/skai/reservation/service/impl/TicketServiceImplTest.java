package io.skai.reservation.service.impl;

import io.skai.reservation.dto.TicketDto;
import io.skai.reservation.exception.EntityDoesNotExistsException;
import io.skai.reservation.jooq.tables.pojos.Ticket;
import io.skai.reservation.mapper.TicketMapper;
import io.skai.reservation.repository.TicketRepository;
import io.skai.reservation.validator.FlightValidator;
import io.skai.reservation.validator.PassengerValidator;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class TicketServiceImplTest {

    private final TicketRepository ticketRepository = Mockito.mock(TicketRepository.class);
    private final TicketMapper ticketMapper = Mockito.mock(TicketMapper.class);
    private final PassengerValidator passengerValidator = Mockito.mock(PassengerValidator.class);
    private final FlightValidator flightValidator = Mockito.mock(FlightValidator.class);

    private final TicketServiceImpl ticketService =
            new TicketServiceImpl(ticketRepository, ticketMapper, flightValidator, passengerValidator);

    @Test
    void whenDbHasOneItemGetAllShouldReturnOneTicket() {
        Ticket ticket = new Ticket(1L, 1L, 1L, new BigDecimal(120), "1B");
        TicketDto ticketDto = new TicketDto(1L, 1L, 1L, new BigDecimal(120), "1B");

        when(ticketMapper.ticketToTicketDto(ticket)).thenReturn(ticketDto);
        when(ticketRepository.selectAll()).thenReturn(List.of(ticket));

        List<TicketDto> allTickets = ticketService.getAllTickets();

        assertThat(allTickets).hasSize(1)
                .contains(ticketDto);

        verify(ticketRepository).selectAll();
        verify(ticketMapper).ticketToTicketDto(ticket);
    }

    @Test
    void whenCreateOneTicketThenTicketRepositoryShouldInsertOnce() {
        Ticket ticket = new Ticket(1L, 1L, 1L, new BigDecimal(120), "1B");
        TicketDto ticketDto = new TicketDto(1L, 1L, 1L, new BigDecimal(120), "1B");

        when(ticketMapper.ticketDtoToTicket(ticketDto)).thenReturn(ticket);

        ticketService.create(ticketDto);

        verify(ticketRepository).insert(ticket);
        verify(ticketMapper).ticketDtoToTicket(ticketDto);
        verify(flightValidator).validate(ticket.getFlightId());
        verify(passengerValidator).validate(ticket.getPassengerId());
    }

    @Test
    void whenFlightWithIdDoesNotExistCreateShouldThrowException() {
        Ticket ticket = new Ticket(1L, 1L, 1L, new BigDecimal(120), "1B");
        TicketDto ticketDto = new TicketDto(1L, 1L, 1L, new BigDecimal(120), "1B");

        when(ticketMapper.ticketDtoToTicket(ticketDto)).thenReturn(ticket);

        doThrow(new EntityDoesNotExistsException("Custom message"))
                .when(flightValidator).validate(ticket.getFlightId());

        assertThatThrownBy(() -> ticketService.create(ticketDto))
                .isInstanceOf(EntityDoesNotExistsException.class)
                .hasMessage("Custom message");
    }

    @Test
    void whenPassengerWithIdDoesNotExistCreateShouldThrowException() {
        Ticket ticket = new Ticket(1L, 1L, 1L, new BigDecimal(120), "1B");
        TicketDto ticketDto = new TicketDto(1L, 1L, 1L, new BigDecimal(120), "1B");

        when(ticketMapper.ticketDtoToTicket(ticketDto)).thenReturn(ticket);

        doThrow(new EntityDoesNotExistsException("Passenger is not exist"))
                .when(passengerValidator).validate(ticket.getPassengerId());

        assertThatThrownBy(() -> ticketService.create(ticketDto))
                .isInstanceOf(EntityDoesNotExistsException.class)
                .hasMessage("Passenger is not exist");
    }
}