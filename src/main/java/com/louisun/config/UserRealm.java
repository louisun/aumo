package com.louisun.config;

import com.louisun.model.User;
import com.louisun.service.LoginService;
import com.louisun.util.constant.SessionKeyConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.List;


@Slf4j
public class UserRealm extends AuthorizingRealm {
    @Autowired
    @Lazy
    private LoginService loginService;






    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // authenticationInfo 交给 AuthenticatingRealm 使用 CredentialsMatcher 与 authenticationToken 进行密码匹配，还可以自定义实现
        String email = (String)token.getPrincipal();
        User user = loginService.getUser(email);
        if(user == null){
            throw new UnknownAccountException();
        }

        return new SimpleAuthenticationInfo(
                user.getEmail(),
                user.getPassword(),
                getName()                   // 获取 Realm Name
        );

    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Session session = SecurityUtils.getSubject().getSession();
        List<String> permissionList = (List<String>) session.getAttribute(SessionKeyConstant.SESSION_USER_PERMISSION);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addStringPermissions(permissionList);
        return authorizationInfo;
    }
}
