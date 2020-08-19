package com.kwgdev.ecommerce.DAO;

import com.kwgdev.ecommerce.Entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * created by kw on 8/19/2020 @ 7:29 AM
 */
// name of JSON entry = "productCategory", and path for the entry = "/product-category"
@RepositoryRestResource(collectionResourceRel = "productCategory", path = "product-category")
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
}
