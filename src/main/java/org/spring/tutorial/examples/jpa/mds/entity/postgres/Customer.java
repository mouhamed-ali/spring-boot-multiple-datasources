package org.spring.tutorial.examples.jpa.mds.entity.postgres;

import org.spring.tutorial.examples.jpa.mds.entity.AbstractCustomer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Customer extends AbstractCustomer {

    @Column(name = "birth_place")
    private String birthPlace;

    // if you don't to eagerly load the children just ignore them with @JsonIgnore annotation
    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    private Set<Account> accounts;

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        return super.toString().concat(
                " { birthPlace='" + birthPlace + "'}");
    }
}
