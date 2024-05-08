package com.springms.productService.Service;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.springms.productService.Entity.Product;
import com.springms.productService.Exeception.ProductServiceCustomException;
import com.springms.productService.Model.ProductRequest;
import com.springms.productService.Repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.beans.BeanUtils.*;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public long addProduct(ProductRequest productRequest) {
        log.info("Adding product");

        Product product = Product.builder().productName(productRequest.getName()).quantity(productRequest.getQuantity()).price(productRequest.getPrice()).productImageUrl(productRequest.getProductImageUrl()).productDescription(productRequest.getProductDescription()).build();
        productRepository.save(product);

        log.info("Product created");
        return product.getProductId();
    }

    @Override
    public Product getProductById(Long productId) {
        return productRepository.findById(productId).orElseThrow(()-> new ProductServiceCustomException("Product not found", "PRODUCT_NOT_FOUND"));
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public void reduceQuantity(long productId, long quantity) {
        log.info("Reduce Quantity {} for Id: {}", quantity, productId);
        Product product = this.getProductById(productId);
        long q = product.getQuantity();
        if(q < quantity){
            throw new ProductServiceCustomException("No sufficient Quantity", "NO_SUFFICIENT_QUANTITY");
        }
        product.setQuantity(q - quantity);
        log.info("Product quantity updated from {} to {}", q, product.getQuantity());
        productRepository.save(product);
    }
}
