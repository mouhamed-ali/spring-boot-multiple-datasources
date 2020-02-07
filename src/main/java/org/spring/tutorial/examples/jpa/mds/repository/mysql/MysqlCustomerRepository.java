package org.spring.tutorial.examples.jpa.mds.repository.mysql;

import org.spring.tutorial.examples.jpa.mds.entity.mysql.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MysqlCustomerRepository extends JpaRepository<Customer, Integer> {
}
