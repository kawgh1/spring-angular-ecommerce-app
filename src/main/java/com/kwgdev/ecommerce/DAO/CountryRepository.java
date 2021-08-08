package com.kwgdev.ecommerce.DAO;

import com.kwgdev.ecommerce.Entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * created by kw on 8/27/2020 @ 11:09 AM
 */

//@CrossOrigin("http://localhost:4200")


//@CrossOrigin("https://spring-angular-ecommerce-front.herokuapp.com/")
@RepositoryRestResource(collectionResourceRel = "countries", path = "countries")
public interface CountryRepository extends JpaRepository<Country, Integer> {
}
