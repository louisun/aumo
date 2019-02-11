package com.louisun.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.louisun.service.LogoutService;
import com.louisun.util.JsonResult;
import com.louisun.util.constant.ErrorEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LogoutServiceImpl implements LogoutService {
    /**
     * 退出登录
     * @return JSONObject
     */
    @Override
    public JSONObject logout() {
        Subject currentUser = SecurityUtils.getSubject();
        if (SecurityUtils.getSubject().getSession() != null) {
            currentUser.logout();
        }
        else{
            log.warn("登出失败：无会话");
            return JsonResult.errorResult(ErrorEnum.E_2002); // 登出失败
        }
        log.info("登出成功");
        return JsonResult.successResult();
    }
}
