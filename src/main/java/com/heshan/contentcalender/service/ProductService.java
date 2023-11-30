package com.heshan.contentcalender.service;

import com.heshan.contentcalender.POJO.Product;
import com.heshan.contentcalender.wrapper.ProductWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

public interface ProductService {
    ResponseEntity<String> addNewProduct(Map<String, String> requestMap);
    ResponseEntity<List<Product>> getProduct();
    ResponseEntity<String> updateProduct(@RequestBody Map<String, String> requestMap);
    ResponseEntity<String> deleteProduct(Integer id);
    ResponseEntity<String> updateStatus(Map<String, String> requestMap);

    ResponseEntity<List<Product>> getProductByCategory(Integer id);

    ResponseEntity<Product> getProductById(Integer id);
}
