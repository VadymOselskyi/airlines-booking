/*
 * This file is generated by jOOQ.
 */
package io.skai.reservation.jooq.tables.pojos;


import java.io.Serializable;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Airport implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String countryCode;
    private String city;

    public Airport() {}

    public Airport(Airport value) {
        this.id = value.id;
        this.name = value.name;
        this.countryCode = value.countryCode;
        this.city = value.city;
    }

    public Airport(
        Long id,
        String name,
        String countryCode,
        String city
    ) {
        this.id = id;
        this.name = name;
        this.countryCode = countryCode;
        this.city = city;
    }

    /**
     * Getter for <code>airlines-db.airport.id</code>.
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Setter for <code>airlines-db.airport.id</code>.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter for <code>airlines-db.airport.name</code>.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Setter for <code>airlines-db.airport.name</code>.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for <code>airlines-db.airport.country_code</code>.
     */
    public String getCountryCode() {
        return this.countryCode;
    }

    /**
     * Setter for <code>airlines-db.airport.country_code</code>.
     */
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    /**
     * Getter for <code>airlines-db.airport.city</code>.
     */
    public String getCity() {
        return this.city;
    }

    /**
     * Setter for <code>airlines-db.airport.city</code>.
     */
    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Airport other = (Airport) obj;
        if (this.id == null) {
            if (other.id != null)
                return false;
        }
        else if (!this.id.equals(other.id))
            return false;
        if (this.name == null) {
            if (other.name != null)
                return false;
        }
        else if (!this.name.equals(other.name))
            return false;
        if (this.countryCode == null) {
            if (other.countryCode != null)
                return false;
        }
        else if (!this.countryCode.equals(other.countryCode))
            return false;
        if (this.city == null) {
            if (other.city != null)
                return false;
        }
        else if (!this.city.equals(other.city))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.countryCode == null) ? 0 : this.countryCode.hashCode());
        result = prime * result + ((this.city == null) ? 0 : this.city.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Airport (");

        sb.append(id);
        sb.append(", ").append(name);
        sb.append(", ").append(countryCode);
        sb.append(", ").append(city);

        sb.append(")");
        return sb.toString();
    }
}