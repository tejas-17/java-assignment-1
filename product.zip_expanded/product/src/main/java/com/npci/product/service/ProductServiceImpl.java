package com.npci.product.service;

import org.springframework.stereotype.Service;
import com.npci.product.model.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service // Add this annotation
public class ProductServiceImpl implements ProductService {

    private final Map<Integer, Product> productMap = new HashMap<>();
    private final AtomicInteger idGenerator = new AtomicInteger(0);

    @Override
    public Product addProduct(Product product) {
        int productId = idGenerator.incrementAndGet();
        product.setId(productId);
        productMap.put(productId, product);
        return product;
    }

    @Override
    public List<Product> getProducts() {
        return List.copyOf(productMap.values());
    }

    @Override
    public Map<String, Object> generateBills() {
        double totalPrice = 0;
        for (Product product : productMap.values()) {
            totalPrice += product.getPrice() * product.getQuantity();
        }

        double cgst = totalPrice * 0.09;
        double sgst = totalPrice * 0.09;
        double totalTax = cgst + sgst;
        double finalTotal = totalPrice + totalTax;

        Map<String, Object> bill = new HashMap<>();
        bill.put("Products", productMap.values());
        bill.put("Total Price", totalPrice);
        bill.put("Total price without tax", totalPrice - totalTax);
        bill.put("CGST 9%", cgst);
        bill.put("SGST 9%", sgst);
        bill.put("9% of total price", totalTax);
        bill.put("Final Total", finalTotal);

        return bill;
    }
}
