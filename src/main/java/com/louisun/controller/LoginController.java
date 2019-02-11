package com.louisun.controller;

import com.alibaba.fastjson.JSONObject;
import com.louisun.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;


@RestController
@Slf4j
@SessionAttributes(names = {"email"})
public class LoginController {
    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    /**
     * 登录 /login POST
     * @param loginRequest 登录信息：邮箱、密码
     * @param session 会话（存放用户邮箱名）
     * @return JSONObject 登录失败信息 or 用户基本信息
     */
    @PostMapping("/login")
    public JSONObject login(@RequestBody JSONObject loginRequest, HttpSession session){
        JSONObject loginResponse =  loginService.authLogin(loginRequest);
        // 若登录成功，在 Session 中保存用户邮箱
        if(loginResponse.getString("returnCode").equals("200")){
            session.setAttribute("userId", loginResponse.getJSONObject("returnData").getIntValue("userId"));
            log.warn("user id is " + loginResponse.getJSONObject("returnData").getIntValue("userId"));
        }
        return loginResponse;
    }
}
