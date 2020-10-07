package com.kwgdev.ecommerce.DAO;

import com.kwgdev.ecommerce.Entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * created by kw on 8/19/2020 @ 7:29 AM
 */

// without @CrossOrigin, the front-end will fail with a CORS error

//@CrossOrigin("http://localhost:4200") // allows our Spring REST to accept web browser calls from this http origin (angular frontend)

//@CrossOrigin("https://springboot-angular-ecommerce.herokuapp.com/")
// name of JSON entry = "productCategory", and path for the entry = "/product-category"
@RepositoryRestResource(collectionResourceRel = "productCategory", path = "product-category")
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
}
