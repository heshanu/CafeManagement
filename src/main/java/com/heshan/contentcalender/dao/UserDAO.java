package com.heshan.contentcalender.dao;

import com.heshan.contentcalender.POJO.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserDAO extends JpaRepository<User, Integer> {
    User findByEmailId(@Param("email") String email);
    User findByNamePassword(@Param("name") String name,@Param("password") String password);
}
