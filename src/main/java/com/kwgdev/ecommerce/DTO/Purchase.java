package com.kwgdev.ecommerce.DTO;

import com.kwgdev.ecommerce.Entity.Address;
import com.kwgdev.ecommerce.Entity.Customer;
import com.kwgdev.ecommerce.Entity.Order;
import com.kwgdev.ecommerce.Entity.OrderItem;
import lombok.Data;

import java.util.Set;

/**
 * created by kw on 7/29/2021 @ 6:19 AM
 */

@Data
public class Purchase {

    private Customer customer;

    private Address shippingAddress;

    private Address billingAddress;

    private Order order;
    // this set comes in as a JSON array across the web, but it can be transformed to a Java Set with no issue
    private Set<OrderItem> orderItems;
}
