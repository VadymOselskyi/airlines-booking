/*
 * This file is generated by jOOQ.
 */
package io.skai.reservation.jooq.tables.records;


import io.skai.reservation.jooq.tables.Passenger;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record5;
import org.jooq.Row5;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PassengerRecord extends UpdatableRecordImpl<PassengerRecord> implements Record5<Long, String, String, String, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>airlines-db.passenger.id</code>.
     */
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>airlines-db.passenger.id</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>airlines-db.passenger.first_name</code>.
     */
    public void setFirstName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>airlines-db.passenger.first_name</code>.
     */
    public String getFirstName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>airlines-db.passenger.last_name</code>.
     */
    public void setLastName(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>airlines-db.passenger.last_name</code>.
     */
    public String getLastName() {
        return (String) get(2);
    }

    /**
     * Setter for <code>airlines-db.passenger.email</code>.
     */
    public void setEmail(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>airlines-db.passenger.email</code>.
     */
    public String getEmail() {
        return (String) get(3);
    }

    /**
     * Setter for <code>airlines-db.passenger.phone</code>.
     */
    public void setPhone(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>airlines-db.passenger.phone</code>.
     */
    public String getPhone() {
        return (String) get(4);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record5 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row5<Long, String, String, String, String> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    @Override
    public Row5<Long, String, String, String, String> valuesRow() {
        return (Row5) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return Passenger.PASSENGER.ID;
    }

    @Override
    public Field<String> field2() {
        return Passenger.PASSENGER.FIRST_NAME;
    }

    @Override
    public Field<String> field3() {
        return Passenger.PASSENGER.LAST_NAME;
    }

    @Override
    public Field<String> field4() {
        return Passenger.PASSENGER.EMAIL;
    }

    @Override
    public Field<String> field5() {
        return Passenger.PASSENGER.PHONE;
    }

    @Override
    public Long component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getFirstName();
    }

    @Override
    public String component3() {
        return getLastName();
    }

    @Override
    public String component4() {
        return getEmail();
    }

    @Override
    public String component5() {
        return getPhone();
    }

    @Override
    public Long value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getFirstName();
    }

    @Override
    public String value3() {
        return getLastName();
    }

    @Override
    public String value4() {
        return getEmail();
    }

    @Override
    public String value5() {
        return getPhone();
    }

    @Override
    public PassengerRecord value1(Long value) {
        setId(value);
        return this;
    }

    @Override
    public PassengerRecord value2(String value) {
        setFirstName(value);
        return this;
    }

    @Override
    public PassengerRecord value3(String value) {
        setLastName(value);
        return this;
    }

    @Override
    public PassengerRecord value4(String value) {
        setEmail(value);
        return this;
    }

    @Override
    public PassengerRecord value5(String value) {
        setPhone(value);
        return this;
    }

    @Override
    public PassengerRecord values(Long value1, String value2, String value3, String value4, String value5) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached PassengerRecord
     */
    public PassengerRecord() {
        super(Passenger.PASSENGER);
    }

    /**
     * Create a detached, initialised PassengerRecord
     */
    public PassengerRecord(Long id, String firstName, String lastName, String email, String phone) {
        super(Passenger.PASSENGER);

        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setPhone(phone);
    }

    /**
     * Create a detached, initialised PassengerRecord
     */
    public PassengerRecord(io.skai.reservation.jooq.tables.pojos.Passenger value) {
        super(Passenger.PASSENGER);

        if (value != null) {
            setId(value.getId());
            setFirstName(value.getFirstName());
            setLastName(value.getLastName());
            setEmail(value.getEmail());
            setPhone(value.getPhone());
        }
    }
}