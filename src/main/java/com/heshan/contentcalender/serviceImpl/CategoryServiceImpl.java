package com.heshan.contentcalender.serviceImpl;

import com.google.common.base.Strings;
import com.heshan.contentcalender.POJO.Category;
import com.heshan.contentcalender.constant.CafeConstant;
import com.heshan.contentcalender.dao.CategoryDAO;
import com.heshan.contentcalender.service.CategoryService;
import com.heshan.contentcalender.utils.CafeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

@Autowired
    CategoryDAO categoryDAO;
    @Override
    public ResponseEntity<String> addCategory(Map<String, String> requestMap) {
        try{
            if(validateCategoryMap(requestMap,false)){
                categoryDAO.save(getCategoryFromMap(requestMap,false));
                return CafeUtils.getResponseEntity("Category Added!", HttpStatus.OK);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstant.Something_Went_Wrong, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<Category>> getAllCategory(String filterValue) {
        try{
            if(!Strings.isNullOrEmpty(filterValue) && filterValue.equalsIgnoreCase("true")){
                    return new ResponseEntity<List<Category>>(categoryDAO.findAll(),HttpStatus.OK);

            }
            return new ResponseEntity<>(categoryDAO.findAll(),HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<List<Category>>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<String> updateCategory(Map<String, String> requestMap) {
        try{
          int catId=Integer.parseInt(requestMap.get("id"));
         if(validateCategoryMap(requestMap,true)){
             Optional<Category> categoryOptional=categoryDAO.findById(requestMap.get("id"));
                if(!categoryOptional.isEmpty()){
                    Category category=categoryDAO.findById(requestMap.get("id")).get();
                    category.setCname(requestMap.get("name"));
                    categoryDAO.save(category);
                }else return CafeUtils.getResponseEntity("Category Updated!", HttpStatus.BAD_REQUEST);

             return CafeUtils.getResponseEntity("Category Updated!", HttpStatus.OK);
         }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstant.Something_Went_Wrong, HttpStatus.BAD_REQUEST);
    }

    private boolean validateCategoryMap(Map<String, String> requestMap, boolean validateId) {
        if(requestMap.containsKey("name")){
            if(requestMap.containsKey("id") && validateId){
                return true;
            }
            else if(!validateId){
                return true;
            }

        }
        return false;
    }
    private Category getCategoryFromMap(Map<String, String> requestMap,Boolean isAdd){
        Category category = new Category();
        if(isAdd){
            category.setCid(Integer.parseInt(requestMap.get("id")));
        }
        category.setCname(requestMap.get("name"));
        return category;
    }
}
