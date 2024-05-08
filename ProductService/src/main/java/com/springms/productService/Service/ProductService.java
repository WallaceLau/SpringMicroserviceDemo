package com.springms.productService.Service;

import com.springms.productService.Entity.Product;
import com.springms.productService.Model.ProductRequest;

import java.util.List;

public interface ProductService {
    long addProduct(ProductRequest productRequest);

    Product getProductById(Long productId);

    List<Product> getAllProduct();

    void reduceQuantity(long productId, long quantity);
}
