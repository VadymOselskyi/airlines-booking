package io.skai.reservation.repository;



import io.skai.reservation.model.AirportModel;

import java.util.List;

public interface AirportRepository {

    AirportModel insert(AirportModel airport);

    List<AirportModel> selectAll();
    AirportModel selectOneById(Long id);
}
