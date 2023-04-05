package io.skai.reservation.consumer;

import io.skai.reservation.model.TicketModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ticket", url = "http://localhost:8081")
public interface TicketClient {

    @GetMapping("/user/{id}/ticket")
    List<TicketModel> getTicketsByUserId(@PathVariable long id);
}

