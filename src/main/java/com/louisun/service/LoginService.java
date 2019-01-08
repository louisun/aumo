package com.louisun.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.louisun.model.User;

public interface LoginService {
    /**
     * 验证登录
     * @param
     * @return
     * @Date 2018-12-26
     **/
    JSONObject authLogin(JSONObject jsonObject);

    /**
     * 根据用户名和密码查询对应的用户
     * @param email 邮箱
     * @return User
     * @Date 2018-12-26
     **/

    User getUser(String email);

    /**
     * 查询当前用户权限信息
     * @param
     * @return
     * @Date 2018-12-26
     **/
    JSONObject getInfo();

    /**
     * 退出登录
     * @param
     * @return
     * @Date 2018-12-26
     **/
    JSONObject logout();
}
