/*
 * This file is generated by jOOQ.
 */
package io.skai.reservation.jooq.tables.records;


import io.skai.reservation.jooq.tables.Ticket;

import java.math.BigDecimal;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TicketRecord extends UpdatableRecordImpl<TicketRecord> implements Record4<Long, Long, Long, BigDecimal> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>airlines-db.ticket.id</code>.
     */
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>airlines-db.ticket.id</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>airlines-db.ticket.passenger_id</code>.
     */
    public void setPassengerId(Long value) {
        set(1, value);
    }

    /**
     * Getter for <code>airlines-db.ticket.passenger_id</code>.
     */
    public Long getPassengerId() {
        return (Long) get(1);
    }

    /**
     * Setter for <code>airlines-db.ticket.flight_id</code>.
     */
    public void setFlightId(Long value) {
        set(2, value);
    }

    /**
     * Getter for <code>airlines-db.ticket.flight_id</code>.
     */
    public Long getFlightId() {
        return (Long) get(2);
    }

    /**
     * Setter for <code>airlines-db.ticket.price</code>.
     */
    public void setPrice(BigDecimal value) {
        set(3, value);
    }

    /**
     * Getter for <code>airlines-db.ticket.price</code>.
     */
    public BigDecimal getPrice() {
        return (BigDecimal) get(3);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record4 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row4<Long, Long, Long, BigDecimal> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    @Override
    public Row4<Long, Long, Long, BigDecimal> valuesRow() {
        return (Row4) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return Ticket.TICKET.ID;
    }

    @Override
    public Field<Long> field2() {
        return Ticket.TICKET.PASSENGER_ID;
    }

    @Override
    public Field<Long> field3() {
        return Ticket.TICKET.FLIGHT_ID;
    }

    @Override
    public Field<BigDecimal> field4() {
        return Ticket.TICKET.PRICE;
    }

    @Override
    public Long component1() {
        return getId();
    }

    @Override
    public Long component2() {
        return getPassengerId();
    }

    @Override
    public Long component3() {
        return getFlightId();
    }

    @Override
    public BigDecimal component4() {
        return getPrice();
    }

    @Override
    public Long value1() {
        return getId();
    }

    @Override
    public Long value2() {
        return getPassengerId();
    }

    @Override
    public Long value3() {
        return getFlightId();
    }

    @Override
    public BigDecimal value4() {
        return getPrice();
    }

    @Override
    public TicketRecord value1(Long value) {
        setId(value);
        return this;
    }

    @Override
    public TicketRecord value2(Long value) {
        setPassengerId(value);
        return this;
    }

    @Override
    public TicketRecord value3(Long value) {
        setFlightId(value);
        return this;
    }

    @Override
    public TicketRecord value4(BigDecimal value) {
        setPrice(value);
        return this;
    }

    @Override
    public TicketRecord values(Long value1, Long value2, Long value3, BigDecimal value4) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached TicketRecord
     */
    public TicketRecord() {
        super(Ticket.TICKET);
    }

    /**
     * Create a detached, initialised TicketRecord
     */
    public TicketRecord(Long id, Long passengerId, Long flightId, BigDecimal price) {
        super(Ticket.TICKET);

        setId(id);
        setPassengerId(passengerId);
        setFlightId(flightId);
        setPrice(price);
    }

    /**
     * Create a detached, initialised TicketRecord
     */
    public TicketRecord(io.skai.reservation.jooq.tables.pojos.Ticket value) {
        super(Ticket.TICKET);

        if (value != null) {
            setId(value.getId());
            setPassengerId(value.getPassengerId());
            setFlightId(value.getFlightId());
            setPrice(value.getPrice());
        }
    }
}
