package org.spring.tutorial.examples.jpa.mds.service.impl;

import org.spring.tutorial.examples.jpa.mds.entity.oracle.Customer;
import org.spring.tutorial.examples.jpa.mds.repository.oracle.OracleCustomerRepository;
import org.spring.tutorial.examples.jpa.mds.repository.postgres.PostgresCustomerRepository;
import org.spring.tutorial.examples.jpa.mds.service.RetrieveData;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class RetrieveDataImpl implements RetrieveData {

    private OracleCustomerRepository oracleOracleCustomerRepository;
    private PostgresCustomerRepository postgresPostgresCustomerRepository;

    public RetrieveDataImpl(OracleCustomerRepository oracleOracleCustomerRepository, PostgresCustomerRepository postgresPostgresCustomerRepository) {
        this.oracleOracleCustomerRepository = oracleOracleCustomerRepository;
        this.postgresPostgresCustomerRepository = postgresPostgresCustomerRepository;
    }

    @Transactional("transactionManager")
    @Override
    public List<Customer> findAllMysqlCustomers() {

        return oracleOracleCustomerRepository.findAll();
    }

    @Transactional("transactionManager")
    @Override
    public void saveOracleCustomers(List<Customer> customers) {

        oracleOracleCustomerRepository.save(customers);
    }

    @Transactional("transactionManager")
    @Override
    public void saveOracleCustomer(Customer customer) {

        oracleOracleCustomerRepository.save(customer);
    }

    @Transactional("postgresTransactionManager")
    @Override
    public List<org.spring.tutorial.examples.jpa.mds.entity.postgres.Customer> findAllPostgresCustomers() {

        return postgresPostgresCustomerRepository.findAll();
    }

    @Transactional("postgresTransactionManager")
    @Override
    public void savePostgresCustomers(List<org.spring.tutorial.examples.jpa.mds.entity.postgres.Customer> customers) {

        postgresPostgresCustomerRepository.save(customers);
    }

    @Transactional("postgresTransactionManager")
    @Override
    public void savePostgresCustomer(org.spring.tutorial.examples.jpa.mds.entity.postgres.Customer customer) {

        postgresPostgresCustomerRepository.save(customer);
    }

}
