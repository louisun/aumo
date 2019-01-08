package com.louisun.dao;

import com.louisun.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDao {
    int deleteByUserId(Integer userId);

    int insertUser(User record);

    User selectUserById(Integer userId);

    User selectUserByEmail(String email);

    int updateUserById(User record);

    int updateUserByEmail(User record);
}