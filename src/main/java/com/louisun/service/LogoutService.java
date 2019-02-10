package com.louisun.service;

import com.alibaba.fastjson.JSONObject;

public interface LogoutService {
    /**
     * 退出登录
     * @param null
     * @return JSONObject
     */
    JSONObject logout();
}
