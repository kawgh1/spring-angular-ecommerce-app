package com.kwgdev.ecommerce.DAO;

import com.kwgdev.ecommerce.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * created by kw on 8/19/2020 @ 7:28 AM
 */
public interface ProductRepository extends JpaRepository <Product, Long> {
}
