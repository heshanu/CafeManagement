package com.heshan.contentcalender.dao;

import com.heshan.contentcalender.POJO.Product;
import com.heshan.contentcalender.wrapper.ProductWrapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductDAO extends JpaRepository<Product,Integer> {

    List<ProductWrapper> getAllProduct();
}
