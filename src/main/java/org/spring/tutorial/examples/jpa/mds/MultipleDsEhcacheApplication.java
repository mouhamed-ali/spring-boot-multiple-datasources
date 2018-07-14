package org.spring.tutorial.examples.jpa.mds;

import org.spring.tutorial.examples.jpa.mds.entity.oracle.Address;
import org.spring.tutorial.examples.jpa.mds.entity.postgres.Account;
import org.spring.tutorial.examples.jpa.mds.entity.postgres.Customer;
import org.spring.tutorial.examples.jpa.mds.service.RetrieveData;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class MultipleDsEhcacheApplication {

	public static void main(String[] args) {

		ApplicationContext context =  SpringApplication.run(MultipleDsEhcacheApplication.class, args);
		RetrieveData retrieveData = (RetrieveData) context.getBean("retrieveDataImpl");

		//postgres init data
		Customer customer = new Customer();
		customer.setId(1);
		customer.setFirstName("postres_first_customer");
		customer.setLastName("postgres_last_customer");
		customer.setEmail("postgres@postgres.com");
		customer.setBirthPlace("Tunisia");
		retrieveData.savePostgresCustomer(customer);

		//oracle init data
		org.spring.tutorial.examples.jpa.mds.entity.oracle.Customer customer1 = new org.spring.tutorial.examples.jpa.mds.entity.oracle.Customer();
		customer1.setId(1);
		customer1.setFirstName("oracle_first_name");
		customer1.setLastName("oracle_last_name");
		customer1.setEmail("oracle@oracle.com");
		customer1.setBirthDate("07/07/07");
		retrieveData.saveOracleCustomer(customer1);


		System.out.println("################################################ Oracle Customers ################################################\n");
		retrieveData.findAllMysqlCustomers().forEach(System.out::println);
		System.out.println("##################################################################################################################\n");

		System.out.println("################################################ Postgres Customers ##############################################\n");
		retrieveData.findAllPostgresCustomers().forEach(System.out::println);
		System.out.println("##################################################################################################################\n");
	}
}
