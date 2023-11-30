package com.heshan.contentcalender.rest;

import com.heshan.contentcalender.POJO.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/product")
public interface ProductRest {
    @PostMapping("/add")
    ResponseEntity<String> addNewProduct(@RequestBody Map<String, String> requestMap);

    @GetMapping("/get")
    ResponseEntity<List<Product>> getProduct();

    @PostMapping("/update")
    ResponseEntity<String> updateProduct(@RequestBody Map<String, String> requestMap);

    @PostMapping("/delete/{id}")
    ResponseEntity<String> deleteProduct(@PathVariable Integer id);

    @PostMapping("/updateStatus")
    ResponseEntity<String> updateStatus(@RequestBody Map<String, String> requestMap);

    @GetMapping("/getByCategory/{id}")
    ResponseEntity<List<Product>> getProductByCategory(@PathVariable Integer id);

    @GetMapping("/getById/{id}")
    ResponseEntity<Product> getProductById(@PathVariable Integer id);
}


