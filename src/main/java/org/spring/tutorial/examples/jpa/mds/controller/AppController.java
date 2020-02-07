package org.spring.tutorial.examples.jpa.mds.controller;


import org.spring.tutorial.examples.jpa.mds.entity.mysql.Customer;
import org.spring.tutorial.examples.jpa.mds.service.RetrieveData;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AppController {

    private final RetrieveData retrieveData;

    public AppController(RetrieveData retrieveData) {
        this.retrieveData = retrieveData;
    }

    @GetMapping("/mysql")
    public List<Customer> getAllMysqlCustomers() {

        return retrieveData.findAllMysqlCustomers();
    }

    @PostMapping("/mysql")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createMysqlCustomer(@RequestBody Customer customer) {

        retrieveData.saveMysqlCustomer(customer);
    }

    @GetMapping("/postgres")
    public List<org.spring.tutorial.examples.jpa.mds.entity.postgres.Customer> getAllPostgresCustomers() {

        return retrieveData.findAllPostgresCustomers();
    }

    @PostMapping("/postgres")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createPostgresCustomer(@RequestBody org.spring.tutorial.examples.jpa.mds.entity.postgres.Customer customer) {

        retrieveData.savePostgresCustomer(customer);
    }
}
