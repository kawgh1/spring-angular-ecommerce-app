package com.kwgdev.ecommerce.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * created by kw on 7/29/2021 @ 5:37 AM
 */

@Entity
@Table(name="address")
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="street")
    private String street;

    @Column(name="city")
    private String city;

    @Column(name="state")
    private String state;

    @Column(name="country")
    private String country;

    @Column(name="zip_code")
    private String zipCode;

    @OneToOne
    @PrimaryKeyJoinColumn // join using primary keys by default keys have same name - hibernate will figure it out for each entity
    private Order order;
}
