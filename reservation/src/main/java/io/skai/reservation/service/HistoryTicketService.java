package io.skai.reservation.service;

import io.skai.reservation.dto.HistoryTicketDto;

import java.util.List;

public interface HistoryTicketService {

    List<HistoryTicketDto> prepareHistory();
}