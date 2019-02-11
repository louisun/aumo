package com.louisun.service;

import com.alibaba.fastjson.JSONObject;

public interface LogoutService {
    /**
     * 退出登录
     * @return JSONObject
     */
    JSONObject logout();
}
