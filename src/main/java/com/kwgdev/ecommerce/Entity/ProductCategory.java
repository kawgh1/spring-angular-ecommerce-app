package com.kwgdev.ecommerce.Entity;

import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * created by kw on 8/19/2020 @ 7:24 AM
 */
@Entity
@Table(name = "product_category")
// @Data -- known Lombok bug when using ManyToMany and ManyToOne
@Getter
@Setter
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "category_name")
    private String categoryName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    private Set<Product> products;
}
