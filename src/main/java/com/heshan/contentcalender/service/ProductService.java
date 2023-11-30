package com.heshan.contentcalender.service;

import com.heshan.contentcalender.POJO.Product;
import com.heshan.contentcalender.wrapper.ProductWrapper;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ProductService {
    ResponseEntity<String> addNewProduct(Map<String, String> requestMap);

    ResponseEntity<List<Product>> getProduct();
}
