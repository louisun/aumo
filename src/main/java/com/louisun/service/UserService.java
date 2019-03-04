package com.louisun.service;

import com.alibaba.fastjson.JSONObject;
import com.louisun.dto.UserRankInfo;
import com.louisun.model.User;

import java.util.List;

public interface UserService {

    List<UserRankInfo> getUserRankInfoList();

    /**
     * 根据用户id得到用户对象
     * @param id  用户id
     * @return JSONObject
     */
    User getUserById(int id);



    /**
     * 根据用户id更新用户信息
     * @param user 用户对象
     * @return int
     */
    int updateUserById(User user);



    /**
     * 新增用户
     * @param user 用户对象
     * @return int
     */
    int insertUser(User user);

    int banUser(int id);

}
