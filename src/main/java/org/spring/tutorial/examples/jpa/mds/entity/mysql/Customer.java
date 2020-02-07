package org.spring.tutorial.examples.jpa.mds.entity.mysql;

import org.spring.tutorial.examples.jpa.mds.entity.AbstractCustomer;

import javax.persistence.*;

@Entity
@Table(name = "CUSTOMER")
public class Customer extends AbstractCustomer {

    @Column(name = "birth_date")
    private String birthDate;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return super.toString().concat(
                " { birthDate='" + birthDate + "'}");
    }
}
