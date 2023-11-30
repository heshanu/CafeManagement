package com.heshan.contentcalender.dao;

import com.heshan.contentcalender.POJO.Product;
import com.heshan.contentcalender.POJO.User;
import com.heshan.contentcalender.wrapper.ProductWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

public interface ProductDAO extends JpaRepository<Product,Integer> {

    List<ProductWrapper> getAllProduct();
    void update(Map<String, String> requestMap);
    List<Product> findByCategory(@Param("id") Integer id);
}
