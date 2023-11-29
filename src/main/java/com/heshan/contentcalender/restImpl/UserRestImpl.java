package com.heshan.contentcalender.restImpl;

import com.heshan.contentcalender.POJO.User;
import com.heshan.contentcalender.constant.CafeConstant;
import com.heshan.contentcalender.rest.UserRest;
import com.heshan.contentcalender.service.UserService;
import com.heshan.contentcalender.utils.CafeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class UserRestImpl implements UserRest {
    @Autowired
    UserService userService;
    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        try{
            return userService.signUp(requestMap);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(CafeConstant.Something_Went_Wrong, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        try{
            return userService.login(requestMap);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(CafeConstant.Something_Went_Wrong, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public List<User> getUser() {
        return userService.findAll();
    }

}
