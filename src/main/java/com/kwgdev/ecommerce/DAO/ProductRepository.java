package com.kwgdev.ecommerce.DAO;

import com.kwgdev.ecommerce.Entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * created by kw on 8/19/2020 @ 7:28 AM
 */
// Origin is more than just the hostname

    // Origin is protocol + hostname + port number

    // Same Origin
    // http://localhost:4200 == http://localhost:4200

    // Different Origin
    // http://localhost:4200 != http://localhost:8080

// without @CrossOrigin, the front-end will fail with a CORS error

//@CrossOrigin("http://localhost:4200") // allows our Spring REST to accept web browser calls from this http origin (angular frontend)

//@CrossOrigin("https://spring-angular-ecommerce-front.herokuapp.com/")
@RepositoryRestResource
public interface ProductRepository extends JpaRepository <Product, Long> {

    Page<Product> findByCategoryId(@RequestParam("id") Long id, Pageable pageable);

    // used in the product search by keyword
    Page<Product> findByNameContaining(@RequestParam("name") String name, Pageable pageable);
}
