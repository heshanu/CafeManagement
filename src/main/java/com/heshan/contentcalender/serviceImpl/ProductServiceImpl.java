package com.heshan.contentcalender.serviceImpl;

import com.heshan.contentcalender.POJO.Category;
import com.heshan.contentcalender.POJO.Product;
import com.heshan.contentcalender.POJO.User;
import com.heshan.contentcalender.constant.CafeConstant;
import com.heshan.contentcalender.dao.ProductDAO;
import com.heshan.contentcalender.service.ProductService;
import com.heshan.contentcalender.utils.CafeUtils;
import com.heshan.contentcalender.wrapper.ProductWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

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
    @Override
    public ResponseEntity<String> updateProduct(Map<String, String> requestMap) {
        try{
            int id = Integer.parseInt(requestMap.get("id"));
            if(productDAO.existsById(id)){
                Product product = productDAO.findById(id).get();
                product.setStatus(requestMap.get("status"));
                product.setName(requestMap.get("name"));
                product.setDescription(requestMap.get("description"));
                product.setPrice(Integer.parseInt(requestMap.get("price")));
                product.setCategory(product.getCategory());


                productDAO.save(product);
                return CafeUtils.getResponseEntity("Product Updated!", HttpStatus.OK);
            }
            else{
                return CafeUtils.getResponseEntity("Unable to update product", HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception e){

            return CafeUtils.getResponseEntity(CafeConstant.Something_Went_Wrong, HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
    @Override
    public ResponseEntity<String> deleteProduct(Integer id) {
        try{

          Optional<Product> optional=productDAO.findById(id);
                if(!optional.isEmpty()){
                    Product product=optional.get();
                    product.setStatus("false");
                    productDAO.save(product);
                    return new ResponseEntity<>("Product Deleted Successfully",HttpStatus.OK);
                }
                else return new ResponseEntity<>("product id isnot exits",HttpStatus.BAD_REQUEST);

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Unable to delete",HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @Override
    public ResponseEntity<String> updateStatus(Map<String, String> requestMap) {
        try{
            Optional<Product> optional=productDAO.findById(Integer.parseInt(requestMap.get("id")));
            if(!optional.isEmpty()){
                Product product=optional.get();
                product.setStatus(requestMap.get("status"));
                productDAO.save(product);
                return new ResponseEntity<>("Status Updated Successfully",HttpStatus.OK);
            }
            else return new ResponseEntity<>("unable to update with this id",HttpStatus.BAD_REQUEST);

        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("unable to update with this id",HttpStatus.BAD_REQUEST);
    }
    @Override
    public ResponseEntity<List<Product>> getProductByCategory(Integer id) {
        try{
            List<Product> productList=productDAO.findByCategory(id);
            if(!Objects.isNull(productList)){
                return new ResponseEntity<>(productList,HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(productList,HttpStatus.BAD_REQUEST);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }
    @Override
    public ResponseEntity<Product> getProductById(Integer id) {
        try{
            Optional<Product> optional=productDAO.findById(id);
            if(!optional.isEmpty()){
                Product product=optional.get();
                return new ResponseEntity<>(product,HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.OK);
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
