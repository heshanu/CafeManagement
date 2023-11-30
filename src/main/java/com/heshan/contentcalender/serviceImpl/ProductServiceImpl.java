package com.heshan.contentcalender.serviceImpl;

import com.heshan.contentcalender.POJO.Category;
import com.heshan.contentcalender.POJO.Product;
import com.heshan.contentcalender.constant.CafeConstant;
import com.heshan.contentcalender.dao.ProductDAO;
import com.heshan.contentcalender.service.ProductService;
import com.heshan.contentcalender.utils.CafeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDAO productDAO;
    @Override
    public ResponseEntity<String> addNewProduct(Map<String, String> requestMap) {
        System.out.println(requestMap.get("name"));
        try{
            if(validateProductMap(requestMap,false)){
                Product pr=getProductFromMap(requestMap,false);
                if(!Objects.isNull(pr)){
                    productDAO.save(getProductFromMap(requestMap,false));
                    return CafeUtils.getResponseEntity("Product Added Successfully", HttpStatus.OK);
                }else{
                    return CafeUtils.getResponseEntity("unable to add fetch", HttpStatus.BAD_REQUEST);
                }

            }
            else return CafeUtils.getResponseEntity("unable to add", HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstant.Something_Went_Wrong, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<Product>> getProduct() {
        try{
            List<Product> productList=productDAO.findAll();
            if(!Objects.isNull(productList)){
                return new ResponseEntity<>(productList,HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(productList,HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }


    private boolean validateProductMap(Map<String, String> requestMap, boolean validateId) {
            if(requestMap.containsKey("name")){
                if (requestMap.containsKey("id") && validateId){
                    return true;
                }else if(!validateId) return true;
            }
            return false;
    }

    private Product getProductFromMap(Map<String, String> requestMap, boolean isValid) {
        Category category=new Category();
        category.setCid(Integer.parseInt(requestMap.get("categoryId")));
        System.out.println(requestMap.get("categoryId"));

        Product product=new Product();
        if(isValid){
            product.setId(Integer.parseInt(requestMap.get("id")));
        }else {product.setStatus("true");}

        product.setCategory(category);
        product.setName(requestMap.get("name"));
        product.setDescription(requestMap.get("description"));
        product.setPrice(Integer.parseInt(requestMap.get("price")));
        return product;
    }
}
