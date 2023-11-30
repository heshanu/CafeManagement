package com.heshan.contentcalender.dao;

import com.heshan.contentcalender.POJO.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryDAO extends JpaRepository<Category,String>{
    List<Category> getAllCategory();
}
