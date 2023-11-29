package com.heshan.contentcalender.entity;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


@Entity
@DynamicUpdate
@Transactional
@DynamicInsert
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "test")
public class test {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long studentId;

    private  String name;

    private String address;

    private String email;

    private String phone;

    private String password;

    private String role;

    private String status;
}
