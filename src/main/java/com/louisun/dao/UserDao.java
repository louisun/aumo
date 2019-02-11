package com.louisun.dao;

import com.louisun.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface UserDao {
    /**
     * 根据用户 ID 删除用户
     */
    int deleteByUserId(Integer userId);

    /**
     * 插入用户（字段只有邮箱、密码、昵称）
     */
    int insertUser(User record);

    /**
     * 通过用户 ID 查找用户
     */
    User selectUserById(Integer userId);

    /**
     * 通过邮箱查找用户
     */
    User selectUserByEmail(String email);

    /**
     * 通过用户 ID 更新用户
     */
    int updateUserById(User record);

    /**
     * 通过邮箱名更新用户
     */
    int updateUserByEmail(User record);
}