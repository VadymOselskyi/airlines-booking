package io.skai.historicaldata.repository;

import io.skai.historicaldata.model.HistoricalTicket;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends CrudRepository <HistoricalTicket, Long> {
    @Query("SELECT * FROM history_ticket WHERE email = :email")
    List<HistoricalTicket> findAll(@Param("email") String email);
}