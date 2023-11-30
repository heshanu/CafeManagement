package com.heshan.contentcalender.restImpl;

import com.heshan.contentcalender.POJO.Category;
import com.heshan.contentcalender.constant.CafeConstant;
import com.heshan.contentcalender.rest.CategoryRest;
import com.heshan.contentcalender.service.CategoryService;
import com.heshan.contentcalender.utils.CafeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class CategoryRestImpl implements CategoryRest {
    @Autowired
    private CategoryService categoryService;

    @Override
    public ResponseEntity<String> addCategory(Map<String, String> requestMap) {
        try {
         return categoryService.addCategory(requestMap);

        } catch (Exception e) {
            return new ResponseEntity<>("Unable to add!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<Category>> getCategory(String filterValue) {
        try{
                return categoryService.getAllCategory(filterValue);
        }
        catch (Exception e){
            e.printStackTrace();
        }
         return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<String> updateCategory(Map<String, String> requestMap) {
        try{
            return categoryService.updateCategory(requestMap);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
