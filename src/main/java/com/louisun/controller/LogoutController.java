package com.louisun.controller;

import com.alibaba.fastjson.JSONObject;
import com.louisun.service.LogoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogoutController {
    private final LogoutService logoutService;

    @Autowired
    public LogoutController(LogoutService logoutService) {
        this.logoutService = logoutService;
    }


    /**
     * 退出登录 /logout POST
     * @return JSONObject
     */
    @PostMapping("/logout")
    public JSONObject logout(){
        return logoutService.logout();
    }
}

