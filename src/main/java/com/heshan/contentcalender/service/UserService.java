package com.heshan.contentcalender.service;

import com.heshan.contentcalender.POJO.User;
import com.heshan.contentcalender.dao.UserDAO;
import com.heshan.contentcalender.wrapper.UserWrapper;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface UserService {

    ResponseEntity<String> signUp(Map<String, String> requestMap);
    List<User> findAll();
    ResponseEntity<String> login(Map<String, String> requestMap);
    ResponseEntity<List<UserWrapper>> getAllUser();

     ResponseEntity<String> update(Map<String, String> requestMap);

    ResponseEntity<String> changePassword(Map<String, String> requestMap);

    ResponseEntity<String> forgotPassword(Map<String, String> requestMap);
}
