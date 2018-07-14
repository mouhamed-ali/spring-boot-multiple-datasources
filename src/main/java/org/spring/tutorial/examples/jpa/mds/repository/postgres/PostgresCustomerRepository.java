package org.spring.tutorial.examples.jpa.mds.repository.postgres;

import org.spring.tutorial.examples.jpa.mds.entity.postgres.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostgresCustomerRepository extends JpaRepository<Customer, Integer> {
}
