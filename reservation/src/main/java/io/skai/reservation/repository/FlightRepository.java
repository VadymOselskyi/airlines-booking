package io.skai.reservation.repository;



import io.skai.reservation.model.FlightModel;

import java.util.List;

public interface FlightRepository {

    FlightModel insert(FlightModel flight);

    List<FlightModel> selectAll();
    FlightModel selectOneById(Long id);
}
