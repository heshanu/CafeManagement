package com.heshan.contentcalender.controller;

import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Data
@RequestMapping("/api/v1")
public class testController {
    @GetMapping("/")
    public void test(){
        System.out.println("test");
    }

    @GetMapping(value = "/testTwo")
    public String test1(){
        return "test";
    }
}
