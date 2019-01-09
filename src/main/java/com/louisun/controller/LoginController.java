package com.louisun.controller;

import com.alibaba.fastjson.JSONObject;
import com.louisun.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;

@RestController
@SessionAttributes(names = {"email"})
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public JSONObject login(@RequestBody JSONObject requestJson, HttpSession session){
        JSONObject jsonResult =  loginService.authLogin(requestJson);
        // 若登录成功，在 Session 中保存用户邮箱
        if(jsonResult.getString("returnCode").equals("200")){
            session.setAttribute("email", requestJson.getString("email"));
        }
        return jsonResult;
    }
}
