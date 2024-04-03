package com.npci.product.controller;



import com.npci.product.model.Product;
import com.npci.product.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        productService.addProduct(product);
        return ResponseEntity.status(HttpStatus.OK).body("Product added successfully");
    }

    @GetMapping("/findAll")
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/generateBill")
    public Map<String, Object> generateBill() {
        return productService.generateBills();
    }
}
