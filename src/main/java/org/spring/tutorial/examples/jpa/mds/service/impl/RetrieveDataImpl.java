package org.spring.tutorial.examples.jpa.mds.service.impl;

import org.spring.tutorial.examples.jpa.mds.entity.mysql.Customer;
import org.spring.tutorial.examples.jpa.mds.repository.mysql.MysqlCustomerRepository;
import org.spring.tutorial.examples.jpa.mds.repository.postgres.PostgresCustomerRepository;
import org.spring.tutorial.examples.jpa.mds.service.RetrieveData;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RetrieveDataImpl implements RetrieveData {

    private MysqlCustomerRepository mysqlCustomerRepository;
    private PostgresCustomerRepository postgresCustomerRepository;

    public RetrieveDataImpl(MysqlCustomerRepository mysqlCustomerRepository, PostgresCustomerRepository postgresCustomerRepository) {
        this.mysqlCustomerRepository = mysqlCustomerRepository;
        this.postgresCustomerRepository = postgresCustomerRepository;
    }

    @Transactional("transactionManager")
    @Override
    public List<Customer> findAllMysqlCustomers() {

        return mysqlCustomerRepository.findAll();
    }

    @Transactional("transactionManager")
    @Override
    public void saveOracleCustomers(List<Customer> customers) {

        mysqlCustomerRepository.save(customers);
    }

    @Transactional("transactionManager")
    @Override
    public void saveOracleCustomer(Customer customer) {

        mysqlCustomerRepository.save(customer);
    }

    @Transactional("postgresTransactionManager")
    @Override
    public List<org.spring.tutorial.examples.jpa.mds.entity.postgres.Customer> findAllPostgresCustomers() {

        return postgresCustomerRepository.findAll();
    }

    @Transactional("postgresTransactionManager")
    @Override
    public void savePostgresCustomers(List<org.spring.tutorial.examples.jpa.mds.entity.postgres.Customer> customers) {

        postgresCustomerRepository.save(customers);
    }

    @Transactional("postgresTransactionManager")
    @Override
    public void savePostgresCustomer(org.spring.tutorial.examples.jpa.mds.entity.postgres.Customer customer) {

        postgresCustomerRepository.save(customer);
    }

}
