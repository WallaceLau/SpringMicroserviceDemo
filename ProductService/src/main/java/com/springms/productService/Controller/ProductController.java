package com.springms.productService.Controller;

import com.springms.productService.Entity.Product;
import com.springms.productService.Model.ProductRequest;
import com.springms.productService.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Long> addProduct(@RequestBody ProductRequest productRequest){
        long productId = productService.addProduct(productRequest);
        return new ResponseEntity<>(productId, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long productId){
        System.out.println(productId);
        return new ResponseEntity<>(productService.getProductById(productId), HttpStatus.OK);
    }

    @PutMapping("/reduceQuantity/{id}")
    public ResponseEntity<Void> reduceQuantoty(
            @PathVariable("id") long productId,
            @RequestParam long quantity
    ){
        productService.reduceQuantity(productId, quantity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getAllProduct")
    public ResponseEntity<List<Product>> getAllProduct(){
        return new ResponseEntity<>(productService.getAllProduct(), HttpStatus.OK);
    }



}
