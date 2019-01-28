package com.louisun.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.louisun.service.LogoutService;
import com.louisun.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LogoutServiceImpl implements LogoutService {
    @Override
    public JSONObject logout() {
        Subject currentUser = SecurityUtils.getSubject();
        try {
            if (SecurityUtils.getSubject().getSession() != null)
            {
                currentUser.logout();
            }
        }catch (Exception e){
        }
        log.warn("登出成功");
        return JsonResult.successResult();
    }
}
