package io.skai.reservation.repository.impl;

import com.kenshoo.pl.entity.*;
import io.skai.reservation.model.Airport;
import io.skai.reservation.pl.AirportEntity;
import io.skai.reservation.pl.command.CreateAirportCommand;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

import static io.skai.reservation.pl.AirportEntity.*;

@Repository
public class AirportPersistenceRepository {

    private final PersistenceLayer<AirportEntity> persistenceLayer;
    private final PLContext plContext;

    public AirportPersistenceRepository(PLContext plContext) {
        this.plContext = plContext;
        this.persistenceLayer = new PersistenceLayer<>(plContext);
    }

    public void save(Collection<CreateAirportCommand> commands) {
        persistenceLayer.create(commands, flowBuilder().build());
    }

    public List<Airport> findAll() {
        return plContext.select(ID, NAME, COUNTRY_CODE, CITY)
                .from(INSTANCE)
                .where(PLCondition.trueCondition())
                .fetch()
                .stream()
                .map(this::convertToAirport)
                .toList();
    }

    public Airport findOne(long id) {
        return plContext.select(ID, NAME, COUNTRY_CODE, CITY)
                .from(INSTANCE)
                .where(ID.eq(id))
                .fetch()
                .stream()
                .map(this::convertToAirport)
                .findFirst()
                .orElseThrow();
    }

    private ChangeFlowConfig.Builder<AirportEntity> flowBuilder() {
        return ChangeFlowConfigBuilderFactory.newInstance(plContext, INSTANCE);
    }

    private Airport convertToAirport(CurrentEntityState currentEntityState) {
        return new Airport(currentEntityState.get(ID),
                currentEntityState.get(NAME),
                currentEntityState.get(COUNTRY_CODE),
                currentEntityState.get(CITY));
    }
}