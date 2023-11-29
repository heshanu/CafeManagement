package com.heshan.contentcalender.POJO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NamedQuery(name = "User.findByEmailId",
        query = "SELECT u FROM User u WHERE u.email = :email")
@NamedQuery(name = "User.findByNamePassword",
        query = "SELECT u FROM User u WHERE u.name = :name and u.password = :password")

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    private String name;
    private String contactNumber;
    private String email;
    private String password;
    private String status;
    private String role;
}
