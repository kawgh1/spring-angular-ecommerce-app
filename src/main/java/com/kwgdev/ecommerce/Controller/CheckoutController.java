package com.kwgdev.ecommerce.Controller;

import com.kwgdev.ecommerce.DTO.Purchase;
import com.kwgdev.ecommerce.DTO.PurchaseResponse;
import com.kwgdev.ecommerce.Service.CheckoutService;
import org.springframework.web.bind.annotation.*;

/**
 * created by kw on 7/29/2021 @ 6:36 AM
 */

//@CrossOrigin("http://localhost:4200") - configured in MyDataRestConfig under cors.addMapping(...).allowedOrigins("...")
@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {

    private CheckoutService checkoutService;

    private CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping("/purchase")
    public PurchaseResponse placeOrder(@RequestBody Purchase purchase) { // JSON object of purchase comes through RequestBody

        // take the JSON purchase object and create a Java Order object through the checkout service
        PurchaseResponse purchaseResponse = checkoutService.placeOrder(purchase);

        // take the newly created purchaseResponse and return it back to the front end as JSON response
        return purchaseResponse;
    }




}
