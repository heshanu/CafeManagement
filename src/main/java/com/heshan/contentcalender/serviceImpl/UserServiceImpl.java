package com.heshan.contentcalender.serviceImpl;

import com.heshan.contentcalender.POJO.User;
import com.heshan.contentcalender.constant.CafeConstant;
import com.heshan.contentcalender.dao.UserDAO;
import com.heshan.contentcalender.service.UserService;
import com.heshan.contentcalender.utils.CafeUtils;
import com.heshan.contentcalender.wrapper.UserWrapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDAO userDAO;
    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        ILoggerFactory loggerFactory = null;
        try {
            if (validateSignUpRequest(requestMap)) {
                User user = userDAO.findByEmailId(requestMap.get("email"));
                if (Objects.isNull(user)) {
                    userDAO.save(getUserFromMap(requestMap));
                    return CafeUtils.getResponseEntity(CafeConstant.SUCCESSFULLY_REGISTERED, HttpStatus.OK);
                } else {
                    return CafeUtils.getResponseEntity(CafeConstant.EMAIL_ALREADY_EXIST, HttpStatus.BAD_REQUEST);
                }
            } else {
                return CafeUtils.getResponseEntity(CafeConstant.INVALID_DATA, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            logger.error("Exception in signUp", e);
            return CafeUtils.getResponseEntity(CafeConstant.Something_Went_Wrong, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        ILoggerFactory loggerFactory = null;
        try {
            if (validateSignUpRequest(requestMap)) {
                User user = userDAO.findByNamePassword(requestMap.get("name"), requestMap.get("password"));
                //User user
                if (Objects.isNull(user)) {
                    //userDAO.save(getUserFromMap(requestMap));
                    return CafeUtils.getResponseEntity(CafeConstant.USER_LOGIN, HttpStatus.OK);
                } else {
                    return CafeUtils.getResponseEntity(CafeConstant.EMAIL_ALREADY_EXIST, HttpStatus.BAD_REQUEST);
                }
            } else {
                return CafeUtils.getResponseEntity(CafeConstant.USER_LOGIN_SUCCESS, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            logger.error("Exception in Login", e);
            return CafeUtils.getResponseEntity(CafeConstant.Something_Went_Wrong, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<UserWrapper>> getAllUser() {
        try {
            return new ResponseEntity<List<UserWrapper>>(userDAO.getAllUsers(), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Exception in getAllUser", e);
            //return CafeUtils.getResponseEntity(CafeConstant.Something_Went_Wrong, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> update(Map<String, String> requestMap) {
        try{
            int id = Integer.parseInt(requestMap.get("id"));
            if(userDAO.existsById(id)){
                User user = userDAO.findById(id).get();
                user.setStatus(requestMap.get("status"));
               // user.setEmail(requestMap.get("email"));
              //  user.setContactNumber(requestMap.get("contactNumber"));
              //
                userDAO.save(user);
                return CafeUtils.getResponseEntity(CafeConstant.USER_UPDATE, HttpStatus.OK);
            }
            else{
                return CafeUtils.getResponseEntity(CafeConstant.UNABLE_TO_UPDATE, HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception e){
            logger.error("Exception in update", e);
            return CafeUtils.getResponseEntity(CafeConstant.Something_Went_Wrong, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public boolean validateSignUpRequest(Map<String, String> requestMap) {
      if( requestMap.containsKey("name") && requestMap.containsKey("email") && requestMap.containsKey("contactNumber") && requestMap.containsKey("password")){
          return true;
      }
      return false;
    }

    public boolean validateLoginRequest(Map<String, String> requestMap) {
        if( requestMap.containsKey("email") && requestMap.containsKey("password")){
            return true;
        }
        return false;
    }

    private User getUserFromMap(Map<String,String> requestMap){
        User user = new User();
        user.setName(requestMap.get("name"));
        user.setEmail(requestMap.get("email"));
        user.setContactNumber(requestMap.get("contactNumber"));
        user.setPassword(requestMap.get("password"));
        return user;
    }


}
