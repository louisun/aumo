package com.louisun.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.louisun.dao.UserDao;
import com.louisun.model.User;
import com.louisun.service.LoginService;
import com.louisun.util.JsonResult;
import com.louisun.util.constant.ErrorEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {
    private final UserDao userdao;

    @Autowired
    public LoginServiceImpl(UserDao userdao) {
        this.userdao = userdao;
    }


    /**
     * 验证登录
     * @param jsonObject 登录信息
     * @return JSONObject
     * @date 2018-12-26
     **/
    @Override
    public JSONObject authLogin(JSONObject jsonObject) {
        String email = jsonObject.getString("email");
        String password = jsonObject.getString("password");
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(email, password);
        try {
            // 尝试用上面 token 登录，失败则抛出认证异常
            currentUser.login(token);

            // 认证成功，获取用户基本信息返回
            User user = userdao.selectUserByEmail(email);
            return getBasicUserInfo(user);
        } catch (AuthenticationException e) {
            // 登录失败异常
            return JsonResult.errorResult(ErrorEnum.E_2001);
        }
    }

    private JSONObject getBasicUserInfo(User user) {
        JSONObject jsonResult = new JSONObject();
        jsonResult.put("userId", user.getUserId());
        jsonResult.put("email", user.getEmail());
        jsonResult.put("nickname", user.getNickname());
        jsonResult.put("moto", user.getMoto());
        jsonResult.put("avatarPath", user.getAvatarPath());

        return JsonResult.successResult(jsonResult);
    }

    /**
     * 根据用户名和密码查询对应的用户
     * @param email 邮箱
     * @return User
     * @date 2018-12-26
     **/
    @Override
    public User getUser(String email) {
        return userdao.selectUserByEmail(email);
    }
}
