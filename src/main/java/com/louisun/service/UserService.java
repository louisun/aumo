package com.louisun.service;

import com.alibaba.fastjson.JSONObject;
import com.louisun.model.User;

public interface UserService {

    public JSONObject getUserById(int id);

    public JSONObject getUserBasicInfoByEmail(String email);

    public JSONObject getUserByEmail(String email);

    public JSONObject updateUserById(User user);

    public JSONObject updateUserByEmail(User user);

    public JSONObject insertUser(User user);

    public JSONObject deleteUserById(int id);
}
