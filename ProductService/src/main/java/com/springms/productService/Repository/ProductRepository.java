package com.springms.productService.Repository;

import com.springms.productService.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
