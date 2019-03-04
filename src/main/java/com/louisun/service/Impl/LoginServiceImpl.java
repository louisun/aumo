package com.louisun.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.louisun.dao.PermissionDao;
import com.louisun.dao.UserDao;
import com.louisun.model.User;
import com.louisun.service.LoginService;
import com.louisun.util.JsonResult;
import com.louisun.util.constant.ErrorEnum;
import com.louisun.util.constant.SessionKeyConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {
    private final UserDao userdao;
    private final PermissionDao permissionDao;

    @Autowired
    public LoginServiceImpl(UserDao userdao, PermissionDao permissionDao) {
        this.userdao = userdao;
        this.permissionDao = permissionDao;
    }


    /**
     * 验证登录
     *
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

            // 认证成功，获取用户基本信息
            User user = userdao.selectUserByEmail(email);

            // 账号若被禁用则禁止登录
            if (user.getDisabled()) {
                log.warn("用户：" + user.getUserId() + "已被封禁，无法登录");
                if (currentUser.getSession() != null) currentUser.logout();
                return JsonResult.errorResult(ErrorEnum.E_2003);
            }

            // 将用户 ID、邮箱、昵称、权限 放入 session 中
            List<String> permissionList = permissionDao.getUserPermissions(user.getUserId());

            JSONObject userInfojson = new JSONObject();
            userInfojson.put("userId", user.getUserId());
            userInfojson.put("email", user.getEmail());
            userInfojson.put("nickname", user.getNickname());

            Session session = SecurityUtils.getSubject().getSession();
            session.setAttribute(SessionKeyConstant.SESSION_USER_INFO, userInfojson);
            session.setAttribute(SessionKeyConstant.SESSION_USER_PERMISSION, permissionList);

            log.info("用户登录：" + user.getUserId() + " : " + user.getEmail() + " : " + user.getNickname());
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
     *
     * @param email 邮箱
     * @return User
     * @date 2018-12-26
     **/
    @Override
    public User getUser(String email) {
        return userdao.selectUserByEmail(email);
    }


    /**
     * 退出登录
     *
     * @return JSONObject
     */
    @Override
    public JSONObject logout() {
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();
        if (session != null) {
            JSONObject sessionInfo = (JSONObject) session.getAttribute("userInfo");
            log.info("用户登出：" + sessionInfo.getString("userId") + " : " + sessionInfo.getString("email") + " : " + sessionInfo.getString("nickname"));
            currentUser.logout();
        } else {
            return JsonResult.errorResult(ErrorEnum.E_2002); // 登出失败
        }
        return JsonResult.successResult("登出成功");
    }
}
