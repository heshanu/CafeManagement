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


}
