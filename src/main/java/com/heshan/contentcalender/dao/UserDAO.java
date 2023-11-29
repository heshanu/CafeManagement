package com.heshan.contentcalender.dao;

import com.heshan.contentcalender.POJO.User;
import com.heshan.contentcalender.wrapper.UserWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserDAO extends JpaRepository<User, Integer> {
    User findByEmailId(@Param("email") String email);
    User findByNamePassword(@Param("name") String name,@Param("password") String password);
    List<UserWrapper> getAllUsers();

@Transactional
@Modifying
    User update(@Param("status") String status,  @Param("id") Integer id);


}
