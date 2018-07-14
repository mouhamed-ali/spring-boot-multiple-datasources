package org.spring.tutorial.examples.jpa.mds.repository.oracle;

import org.spring.tutorial.examples.jpa.mds.entity.oracle.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OracleCustomerRepository extends JpaRepository<Customer, Integer> {
}
