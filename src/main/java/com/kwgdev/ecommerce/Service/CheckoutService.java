package com.kwgdev.ecommerce.Service;

import com.kwgdev.ecommerce.DTO.Purchase;
import com.kwgdev.ecommerce.DTO.PurchaseResponse;

/**
 * created by kw on 7/29/2021 @ 6:24 AM
 */


public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);
}
