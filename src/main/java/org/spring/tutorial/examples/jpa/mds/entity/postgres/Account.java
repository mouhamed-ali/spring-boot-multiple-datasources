package org.spring.tutorial.examples.jpa.mds.entity.postgres;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;

@Entity
public class Account {

    @Id
    private Integer id;

    private Double balance;

    @Column(name = "account_name")
    private String accountName;

    @Column(name = "date_opened")
    private String dateOpened;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="customer_id")
//    @ForeignKey(name="customer_id_FK")
    private Customer customer;

    public Double getBalance() {
        return balance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getDateOpened() {
        return dateOpened;
    }

    public void setDateOpened(String dateOpened) {
        this.dateOpened = dateOpened;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
