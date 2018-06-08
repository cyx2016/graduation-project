package com.cyx.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class HelloController {

    @RequestMapping("/hello")
    public String Test(){
        System.out.println("进入控制层");//
        return "hello world,cyx";
    }

    @RequestMapping("/test")
    public String Test1(){
        int i=1/0;
        return "ok";
    }
}
