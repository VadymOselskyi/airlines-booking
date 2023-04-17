/*
 * This file is generated by jOOQ.
 */
package io.skai.reservation.jooq;


import io.skai.reservation.jooq.tables.Airport;
import io.skai.reservation.jooq.tables.Flight;
import io.skai.reservation.jooq.tables.HistoryTicket;
import io.skai.reservation.jooq.tables.Passenger;
import io.skai.reservation.jooq.tables.Ticket;

import java.util.Arrays;
import java.util.List;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class AirlinesDb extends SchemaImpl {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>airlines-db</code>
     */
    public static final AirlinesDb AIRLINES_DB = new AirlinesDb();

    /**
     * The table <code>airlines-db.airport</code>.
     */
    public final Airport AIRPORT = Airport.AIRPORT;

    /**
     * The table <code>airlines-db.flight</code>.
     */
    public final Flight FLIGHT = Flight.FLIGHT;

    /**
     * The table <code>airlines-db.history_ticket</code>.
     */
    public final HistoryTicket HISTORY_TICKET = HistoryTicket.HISTORY_TICKET;

    /**
     * The table <code>airlines-db.passenger</code>.
     */
    public final Passenger PASSENGER = Passenger.PASSENGER;

    /**
     * The table <code>airlines-db.ticket</code>.
     */
    public final Ticket TICKET = Ticket.TICKET;

    /**
     * No further instances allowed
     */
    private AirlinesDb() {
        super("airlines-db", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.asList(
            Airport.AIRPORT,
            Flight.FLIGHT,
            HistoryTicket.HISTORY_TICKET,
            Passenger.PASSENGER,
            Ticket.TICKET
        );
    }
}
