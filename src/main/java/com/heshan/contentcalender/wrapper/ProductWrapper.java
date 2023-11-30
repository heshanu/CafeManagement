package com.heshan.contentcalender.wrapper;

import com.heshan.contentcalender.POJO.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.sql.In;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductWrapper {
    Integer id;
    String name;
    String description;
    Integer price;
    String status;
    Integer categoryId;
    String categoryName;

    ProductWrapper(int id, String status, String description, String name, int price, int category){
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.status = status;
        this.categoryId = category;

    }


    }
