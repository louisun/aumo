package com.louisun.service;

import com.alibaba.fastjson.JSONObject;
import com.louisun.model.User;

public interface UserService {

    /**
     * 根据用户id得到用户对象
     * @param id  用户id
     * @return JSONObject
     */
    JSONObject getUserById(int id);



    /**
     * 根据邮箱得到用户对象
     * @param email 用户邮箱
     * @return JSONObject
     */
    JSONObject getUserByEmail(String email);

    /**
     * 根据用户id更新用户信息
     * @param user 用户对象
     * @return JSONObject
     */
    JSONObject updateUserById(User user);

    /**
     * 根据用户邮箱更新用户信息
     * @param user 用户对象
     * @return JSONObject
     */
    JSONObject updateUserByEmail(User user);


    /**
     * 新增用户
     * @param user 用户对象
     * @return JSONObject
     */
    JSONObject insertUser(User user);


    /**
     * 根据用户id删除用户
     * @param id 用户 id
     * @return JSONObject
     */
    JSONObject deleteUserById(int id);
}
