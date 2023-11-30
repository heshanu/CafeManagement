package com.heshan.contentcalender.POJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;

@NamedQuery(name = "Product.getAllProduct",query = "select new com.heshan.contentcalender.wrapper.ProductWrapper(p.id,p.status,p.description,p.name,p.price,p.category) from Product p ")
@NamedQuery(name="Product.update",query = "update Product p set p.name=:name,p.description=:description,p.price=:price,p.status=:status,p.category=:category where p.id=:id")
@NamedQuery(name="Product.findByCategory",query = "select new com.heshan.contentcalender.wrapper.ProductWrapper(p.id,p.status,p.description,p.name,p.price,p.category.cid) from Product p where p.category.cid=:id")

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Table(name = "product")
public class Product implements Serializable {
    public static final Long serialVersionUID = 123456L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category",nullable = false)
    @JsonIgnoreProperties(value = {"product", "hibernateLazyInitializer"})
    private Category category;

    private String description;
    private Integer price;
    private String status;

    public Product(int id, String status, String description,String name, int price , Category category){
        this.id=id;
        this.status=status;
        this.description=description;
        this.name=name;
        this.price=price;
        this.category=category;
    }

}
