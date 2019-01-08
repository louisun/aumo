package com.louisun.controller;

import com.alibaba.fastjson.JSONObject;
import com.louisun.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public JSONObject login(@RequestBody JSONObject requestJson){
        return loginService.authLogin(requestJson);
    }


}
