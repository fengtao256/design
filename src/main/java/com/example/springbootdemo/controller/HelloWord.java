package com.example.springbootdemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/helloWord")
public class HelloWord {

    @ResponseBody
    @RequestMapping(value = "/test")
    public Map<String,Object> testHello(HttpServletRequest request, HttpServletResponse response){
        Map<String ,Object> map = new HashMap<String , Object>() ;
        map.put("code","200");
        map.put("msg","OK") ;
        map.put("data","测试返回成功");
        return map;
    }

}
