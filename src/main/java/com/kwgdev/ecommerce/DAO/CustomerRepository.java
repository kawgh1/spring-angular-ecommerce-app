package com.kwgdev.ecommerce.DAO;

import com.kwgdev.ecommerce.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * created by kw on 7/29/2021 @ 6:23 AM
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
