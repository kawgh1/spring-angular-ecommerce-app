package com.kwgdev.ecommerce.DAO;

import com.kwgdev.ecommerce.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * created by kw on 7/29/2021 @ 6:23 AM
 */
@RepositoryRestResource(exported = false) // setting this to false means Spring Data REST will **NOT** publicly expose this repository in the API @ http://localhost:8080/api
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByEmail(String theEmail);
}
