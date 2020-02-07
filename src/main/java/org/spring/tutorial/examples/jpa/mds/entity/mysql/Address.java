package org.spring.tutorial.examples.jpa.mds.entity.mysql;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.spring.tutorial.examples.jpa.mds.entity.AbstractCustomer;

import javax.persistence.*;

@Entity
@Table(name = "ADDRESS")
public class Address {

    @Id
    private Integer id;
    private String building;
    private String street;
    private String country;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "address")
    private Customer customer;

    /*
     * we didn't map all fields
     */

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AbstractCustomer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Address{" +
                "building='" + building + '\'' +
                ", street='" + street + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
