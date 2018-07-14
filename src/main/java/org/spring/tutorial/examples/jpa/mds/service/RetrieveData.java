package org.spring.tutorial.examples.jpa.mds.service;

import org.spring.tutorial.examples.jpa.mds.entity.oracle.Customer;

import java.util.List;

public interface RetrieveData {

    List<Customer> findAllMysqlCustomers();
    void saveOracleCustomers(List<Customer> customers);
    void saveOracleCustomer(Customer customer);
    List<org.spring.tutorial.examples.jpa.mds.entity.postgres.Customer> findAllPostgresCustomers();
    void savePostgresCustomers(List<org.spring.tutorial.examples.jpa.mds.entity.postgres.Customer> customers);
    void savePostgresCustomer(org.spring.tutorial.examples.jpa.mds.entity.postgres.Customer customer);
}
