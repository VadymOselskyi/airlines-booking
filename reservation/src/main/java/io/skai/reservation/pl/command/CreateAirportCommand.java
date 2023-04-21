package io.skai.reservation.pl.command;

import com.kenshoo.pl.entity.CreateEntityCommand;
import io.skai.reservation.pl.AirportEntity;

public class CreateAirportCommand extends CreateEntityCommand<AirportEntity> {
    public CreateAirportCommand() {
        super(AirportEntity.INSTANCE);
    }
}