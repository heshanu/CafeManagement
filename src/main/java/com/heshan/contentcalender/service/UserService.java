package com.heshan.contentcalender.service;

import com.heshan.contentcalender.POJO.User;
import com.heshan.contentcalender.dao.UserDAO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface UserService {

    ResponseEntity<String> signUp(Map<String, String> requestMap);
    List<User> findAll();

    User findByEmail(String email);
}
