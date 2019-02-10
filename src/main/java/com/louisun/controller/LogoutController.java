package com.louisun.controller;

import com.alibaba.fastjson.JSONObject;
import com.louisun.service.LoginService;
import com.louisun.service.LogoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;

@RestController
public class LogoutController {
    @Autowired
    private LogoutService logoutService;


    /**
     * 退出登录 /logout POST
     * @param null
     * @return JSONObject
     */
    @PostMapping("/logout")
    public JSONObject logout(){
        return logoutService.logout();
    }
}

