package com.louisun.controller;

import com.alibaba.fastjson.JSONObject;
import com.louisun.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
public class LoginController {
    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    /**
     * 登录 /login POST
     * @param loginRequest 登录信息：邮箱、密码
     * @return JSONObject 登录失败信息 or 用户基本信息
     */
    @PostMapping("/login")
    public JSONObject login(@RequestBody JSONObject loginRequest){
        JSONObject loginResponse =  loginService.authLogin(loginRequest);
        return loginResponse;
    }

    /**
     * 退出登录 /logout POST
     * @return JSONObject
     */
    @PostMapping("/logout")
    public JSONObject logout(){
        return loginService.logout();
    }
}
