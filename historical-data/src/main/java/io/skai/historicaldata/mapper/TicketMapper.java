package io.skai.historicaldata.mapper;

import io.skai.historicaldata.dto.HistoricalTicketDto;
import io.skai.historicaldata.model.HistoricalTicket;
import org.mapstruct.Mapper;

@Mapper
public interface TicketMapper {
    HistoricalTicketDto historicalTicketToHistoricalTicketDto(HistoricalTicket historicalTicket);
    HistoricalTicket historicalTicketDtoToHistoricalTicket(HistoricalTicketDto historicalTicketDto);
}