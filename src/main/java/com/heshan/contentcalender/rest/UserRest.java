package com.heshan.contentcalender.rest;

import com.heshan.contentcalender.POJO.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@RequestMapping("/user")
public interface UserRest {

    @PostMapping("/signUp")
    public ResponseEntity<String> signUp(@RequestBody(required = true) Map<String, String> requestMap );

    @GetMapping("/")
    public List<User> getUser();

    @GetMapping("/email")
    public User getUserByEmail(@RequestBody String email);
}
