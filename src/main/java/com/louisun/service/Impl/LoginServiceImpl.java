package com.louisun.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.louisun.dao.UserDao;
import com.louisun.model.User;
import com.louisun.service.LoginService;
import com.louisun.util.JsonResult;
import com.louisun.util.constant.ErrorEnum;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    private Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    private UserDao userdao;


    @Override
    public JSONObject authLogin(JSONObject jsonObject) {
        String email = jsonObject.getString("email");
        String password = jsonObject.getString("password");
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(email, password);
        try {
            currentUser.login(token);
            // 获取用户信息
            User user = userdao.selectUserByEmail(email);
            JSONObject jsonResult = new JSONObject();
            jsonResult.put("email", user.getEmail());
            jsonResult.put("nickname", user.getNickname());
            jsonResult.put("moto", user.getMoto());
            jsonResult.put("avatar_path", user.getAvatarPath());
            return JsonResult.successResult(jsonResult);
        } catch (AuthenticationException e) {
            return JsonResult.errorResult(ErrorEnum.E_2001);
        }
    }

    @Override
    public User getUser(String email) {
        return userdao.selectUserByEmail(email);
    }

    @Override
    public JSONObject getInfo() {
        return null;
    }

    @Override
    public JSONObject logout() {
        return null;
    }
}
