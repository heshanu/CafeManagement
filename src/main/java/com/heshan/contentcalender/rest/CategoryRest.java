package com.heshan.contentcalender.rest;

import com.heshan.contentcalender.POJO.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/category")
public interface CategoryRest {
    @PostMapping("/add")
    ResponseEntity<String> addCategory(@RequestBody(required = true) Map<String, String> requestMap);

    @GetMapping("/get")
    ResponseEntity<List<Category>> getCategory(@RequestParam(required =false) String filterValue);

    @PostMapping("/update")
    ResponseEntity<String> updateCategory(@RequestBody(required = true) Map<String, String> requestMap);
}
