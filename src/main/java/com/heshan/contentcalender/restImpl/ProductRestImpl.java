package com.heshan.contentcalender.restImpl;

import com.heshan.contentcalender.POJO.Product;
import com.heshan.contentcalender.rest.ProductRest;
import com.heshan.contentcalender.service.ProductService;
import com.heshan.contentcalender.utils.CafeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class ProductRestImpl implements ProductRest {
    @Autowired
    private ProductService  productService;

    @Override
    public ResponseEntity<String> addNewProduct(Map<String, String> requestMap) {
        try{
            return productService.addNewProduct(requestMap);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return CafeUtils.getResponseEntity("Error", HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<Product>> getProduct() {
        try{
            return productService.getProduct();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
