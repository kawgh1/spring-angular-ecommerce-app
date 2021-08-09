package com.kwgdev.ecommerce.DAO;

import com.kwgdev.ecommerce.Entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * created by kw on 8/8/2021 @ 4:46 PM
 */
@RepositoryRestResource
public interface OrderRepository extends JpaRepository<Order, Long> {

    Page<Order> findByCustomerEmail(@Param("email") String email, Pageable pageable);

    // return a list sorted "ordered by" date created in descending order (most recent order first)
    Page<Order> findByCustomerEmailOrderByDateCreatedDesc(@Param("email") String email, Pageable pageable);
}
