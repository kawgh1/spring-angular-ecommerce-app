package com.kwgdev.ecommerce.DAO;

import com.kwgdev.ecommerce.Entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

/**
 * created by kw on 8/27/2020 @ 11:13 AM
 */
// @CrossOrigin("http://localhost:4200")


@RepositoryRestResource
public interface StateRepository extends JpaRepository<State, Integer> {

    // get states for a specific country code parameter
    List<State> findByCountryCode(@Param("code") String code);
}
