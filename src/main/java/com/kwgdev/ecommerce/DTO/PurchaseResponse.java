package com.kwgdev.ecommerce.DTO;

import lombok.Data;

/**
 * created by kw on 7/29/2021 @ 6:22 AM
 */

// This class is used to send back a Java object as JSON

@Data
public class PurchaseResponse {

    private final String orderTrackingNumber;  // instead of final, we could also use the @NotNull annotation
}
