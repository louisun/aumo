package com.louisun.config;

import com.louisun.model.User;
import com.louisun.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;


@Slf4j
public class UserRealm extends AuthorizingRealm {
    @Autowired
    @Lazy
    private LoginService loginService;




    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 从 token 中
        String email = (String)token.getPrincipal();
        // Realm 中获取数据库信息
        User user = loginService.getUser(email);
        // 用户不存在
        if(user == null){
            // 未知账号异常是账号异常 AccountException 的一种，更是认证异常的一种
            throw new UnknownAccountException();
        }
        // authenticationInfo 交给 AuthenticatingRealm 使用 CredentialsMatcher 与 authenticationToken 进行密码匹配，还可以自定义实现

//        JSONObject jsonSessionObject = new JSONObject();
//        jsonSessionObject.put("email", user.getEmail());
//        jsonSessionObject.put("nickname", user.getNickname());

        // 将用户信息放入 session 中
//        SecurityUtils.getSubject().getSession().setAttribute(SessionKeyEnum.SESSION_USER_INFO,jsonSessionObject);

        return new SimpleAuthenticationInfo(
                user.getEmail(),
                user.getPassword(),
                getName() // 当前 UserRealm 类的方法，获取 Realm Name
        );

    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("执行授权逻辑");
        return null;
    }
}
