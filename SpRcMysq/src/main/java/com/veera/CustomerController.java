package com.veera;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.veera.ResourceNotFoundException;
import com.veera.Customer;
import com.veera.CustomerRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/custm/v1/")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    // get all employees
    @GetMapping("/customer")
    public List < Customer > getAllEmployees() {
        return customerRepository.findAll();
    }

    // create employee rest api
    @PostMapping("/customer")
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    // get employee by id rest api
    @GetMapping("/customer/{id}")
    public ResponseEntity < Customer > getCustomerById(@PathVariable Long id) {
        Customer customer = customerRepository.findById(id)
            .orElseThrow(()-> new ResourceNotFoundException("Customer not exist with id :" + id));
        return ResponseEntity.ok(customer);
    }

    // update employee rest api

    @PutMapping("/customer/{id}")
    public ResponseEntity < Customer > updateCustomer(@PathVariable Long id, @RequestBody Customer customerDetails) {
        Customer customer = customerRepository.findById(id)
            .orElseThrow(()-> new ResourceNotFoundException("Customer not exist with id :" + id));

        customer.setFirstName(customerDetails.getFirstName());
        customer.setLastName(customerDetails.getLastName());
        customer.setEmailId(customerDetails.getEmailId());

        Customer updatedCustomer = customerRepository.save(customer);
        return ResponseEntity.ok(updatedCustomer);
    }

    // delete employee rest api
    @DeleteMapping("/customer/{id}")
    public ResponseEntity < Map < String, Boolean >> deleteCustomer(@PathVariable Long id) {
        Customer customer = customerRepository.findById(id)
            .orElseThrow(()-> new ResourceNotFoundException("Customer not exist with id :" + id));

        customerRepository.delete(customer);
        Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}