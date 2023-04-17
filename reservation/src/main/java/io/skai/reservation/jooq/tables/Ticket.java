/*
 * This file is generated by jOOQ.
 */
package io.skai.reservation.jooq.tables;


import io.skai.reservation.jooq.AirlinesDb;
import io.skai.reservation.jooq.Keys;
import io.skai.reservation.jooq.tables.records.TicketRecord;

import java.math.BigDecimal;
import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function5;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row5;
import org.jooq.Schema;
import org.jooq.SelectField;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Ticket extends TableImpl<TicketRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>airlines-db.ticket</code>
     */
    public static final Ticket TICKET = new Ticket();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TicketRecord> getRecordType() {
        return TicketRecord.class;
    }

    /**
     * The column <code>airlines-db.ticket.id</code>.
     */
    public final TableField<TicketRecord, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>airlines-db.ticket.passenger_id</code>.
     */
    public final TableField<TicketRecord, Long> PASSENGER_ID = createField(DSL.name("passenger_id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>airlines-db.ticket.flight_id</code>.
     */
    public final TableField<TicketRecord, Long> FLIGHT_ID = createField(DSL.name("flight_id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>airlines-db.ticket.price</code>.
     */
    public final TableField<TicketRecord, BigDecimal> PRICE = createField(DSL.name("price"), SQLDataType.DECIMAL(8, 2).nullable(false), this, "");

    /**
     * The column <code>airlines-db.ticket.seat_number</code>.
     */
    public final TableField<TicketRecord, String> SEAT_NUMBER = createField(DSL.name("seat_number"), SQLDataType.VARCHAR(255), this, "");

    private Ticket(Name alias, Table<TicketRecord> aliased) {
        this(alias, aliased, null);
    }

    private Ticket(Name alias, Table<TicketRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>airlines-db.ticket</code> table reference
     */
    public Ticket(String alias) {
        this(DSL.name(alias), TICKET);
    }

    /**
     * Create an aliased <code>airlines-db.ticket</code> table reference
     */
    public Ticket(Name alias) {
        this(alias, TICKET);
    }

    /**
     * Create a <code>airlines-db.ticket</code> table reference
     */
    public Ticket() {
        this(DSL.name("ticket"), null);
    }

    public <O extends Record> Ticket(Table<O> child, ForeignKey<O, TicketRecord> key) {
        super(child, key, TICKET);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : AirlinesDb.AIRLINES_DB;
    }

    @Override
    public Identity<TicketRecord, Long> getIdentity() {
        return (Identity<TicketRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<TicketRecord> getPrimaryKey() {
        return Keys.KEY_TICKET_PRIMARY;
    }

    @Override
    public Ticket as(String alias) {
        return new Ticket(DSL.name(alias), this);
    }

    @Override
    public Ticket as(Name alias) {
        return new Ticket(alias, this);
    }

    @Override
    public Ticket as(Table<?> alias) {
        return new Ticket(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Ticket rename(String name) {
        return new Ticket(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Ticket rename(Name name) {
        return new Ticket(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Ticket rename(Table<?> name) {
        return new Ticket(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row5 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row5<Long, Long, Long, BigDecimal, String> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function5<? super Long, ? super Long, ? super Long, ? super BigDecimal, ? super String, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function5<? super Long, ? super Long, ? super Long, ? super BigDecimal, ? super String, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
