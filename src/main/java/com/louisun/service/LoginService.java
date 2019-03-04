package com.louisun.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.louisun.model.User;

public interface LoginService {
    /**
     * 验证登录
     * @param jsonObject 登录信息
     * @return JSONObject
     * @date 2018-12-26
     **/
    JSONObject authLogin(JSONObject jsonObject);

    /**
     * 退出登录
     * @return JSONObject
     * @date 2018-12-26
     */
    JSONObject logout();

    /**
     * 根据用户名和密码查询对应的用户
     * @param email 邮箱
     * @return User
     * @date 2018-12-26
     **/
    User getUser(String email);


}
