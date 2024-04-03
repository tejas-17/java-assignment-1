package com.npci.product.service;



import com.npci.product.model.Product;

import java.util.List;
import java.util.Map;

public interface ProductService {
    Product addProduct(Product product);
    List<Product> getProducts();
    Map<String, Object> generateBills();
}
