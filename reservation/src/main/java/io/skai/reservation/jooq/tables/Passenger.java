/*
 * This file is generated by jOOQ.
 */
package io.skai.reservation.jooq.tables;


import io.skai.reservation.jooq.AirlinesDb;
import io.skai.reservation.jooq.Keys;
import io.skai.reservation.jooq.tables.records.PassengerRecord;

import java.util.Arrays;
import java.util.List;
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
public class Passenger extends TableImpl<PassengerRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>airlines-db.passenger</code>
     */
    public static final Passenger PASSENGER = new Passenger();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<PassengerRecord> getRecordType() {
        return PassengerRecord.class;
    }

    /**
     * The column <code>airlines-db.passenger.id</code>.
     */
    public final TableField<PassengerRecord, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>airlines-db.passenger.first_name</code>.
     */
    public final TableField<PassengerRecord, String> FIRST_NAME = createField(DSL.name("first_name"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>airlines-db.passenger.last_name</code>.
     */
    public final TableField<PassengerRecord, String> LAST_NAME = createField(DSL.name("last_name"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>airlines-db.passenger.email</code>.
     */
    public final TableField<PassengerRecord, String> EMAIL = createField(DSL.name("email"), SQLDataType.VARCHAR(128).nullable(false), this, "");

    /**
     * The column <code>airlines-db.passenger.phone</code>.
     */
    public final TableField<PassengerRecord, String> PHONE = createField(DSL.name("phone"), SQLDataType.VARCHAR(128).nullable(false), this, "");

    private Passenger(Name alias, Table<PassengerRecord> aliased) {
        this(alias, aliased, null);
    }

    private Passenger(Name alias, Table<PassengerRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>airlines-db.passenger</code> table reference
     */
    public Passenger(String alias) {
        this(DSL.name(alias), PASSENGER);
    }

    /**
     * Create an aliased <code>airlines-db.passenger</code> table reference
     */
    public Passenger(Name alias) {
        this(alias, PASSENGER);
    }

    /**
     * Create a <code>airlines-db.passenger</code> table reference
     */
    public Passenger() {
        this(DSL.name("passenger"), null);
    }

    public <O extends Record> Passenger(Table<O> child, ForeignKey<O, PassengerRecord> key) {
        super(child, key, PASSENGER);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : AirlinesDb.AIRLINES_DB;
    }

    @Override
    public Identity<PassengerRecord, Long> getIdentity() {
        return (Identity<PassengerRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<PassengerRecord> getPrimaryKey() {
        return Keys.KEY_PASSENGER_PRIMARY;
    }

    @Override
    public List<UniqueKey<PassengerRecord>> getUniqueKeys() {
        return Arrays.asList(Keys.KEY_PASSENGER_EMAIL, Keys.KEY_PASSENGER_PHONE);
    }

    @Override
    public Passenger as(String alias) {
        return new Passenger(DSL.name(alias), this);
    }

    @Override
    public Passenger as(Name alias) {
        return new Passenger(alias, this);
    }

    @Override
    public Passenger as(Table<?> alias) {
        return new Passenger(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Passenger rename(String name) {
        return new Passenger(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Passenger rename(Name name) {
        return new Passenger(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Passenger rename(Table<?> name) {
        return new Passenger(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row5 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row5<Long, String, String, String, String> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function5<? super Long, ? super String, ? super String, ? super String, ? super String, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function5<? super Long, ? super String, ? super String, ? super String, ? super String, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
