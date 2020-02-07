package org.spring.tutorial.examples.jpa.mds.service;

import org.spring.tutorial.examples.jpa.mds.entity.mysql.Customer;

import java.util.List;

public interface RetrieveData {

    List<Customer> findAllMysqlCustomers();

    void saveMysqlCustomers(List<Customer> customers);

    void saveMysqlCustomer(Customer customer);

    List<org.spring.tutorial.examples.jpa.mds.entity.postgres.Customer> findAllPostgresCustomers();

    void savePostgresCustomers(List<org.spring.tutorial.examples.jpa.mds.entity.postgres.Customer> customers);

    void savePostgresCustomer(org.spring.tutorial.examples.jpa.mds.entity.postgres.Customer customer);
}
