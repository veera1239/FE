package com.veera;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.veera.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
