package com.springms.productService.Model;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ProductRequest {

    private String name;
    private long price;
    private long quantity;
    private String productImageUrl;
    private String productDescription;
}
