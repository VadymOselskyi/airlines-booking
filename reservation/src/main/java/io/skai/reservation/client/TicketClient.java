package io.skai.reservation.client;

import io.skai.reservation.dto.TicketDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ticket", url = "http://localhost:8081")
public interface TicketClient {

    @GetMapping("/user/{id}")
    List<TicketDto> getTicketsByUserId(@PathVariable("id") long id);
}

