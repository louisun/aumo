package com.louisun.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.louisun.dao.RedisDao;
import com.louisun.dao.UserDao;
import com.louisun.dto.UserRankInfo;
import com.louisun.model.User;
import com.louisun.service.UserService;
import com.louisun.util.JsonResult;
import com.louisun.util.constant.ErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;


    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<UserRankInfo> getUserRankInfoList() {
        return userDao.getUserRankInfoList();
    }

    /**
     * 根据用户id得到用户对象
     * @param id  用户id
     * @return User
     */
    @Override
    @Cacheable(value="user", key="'user_'+#id", unless = "#result == null")
    public User getUserById(int id) {
        return userDao.selectUserById(id);
    }



    /**
     * 新增用户
     * @param user 用户对象
     * @return int
     */
    @Override
    @Transactional
    public int insertUser(User user) {
        return userDao.insertUser(user);
    }


    /**
     * 根据用户id更新用户信息
     * @param user 用户对象
     * @return int
     */
    @Override
    @CacheEvict(value="user", key="'user_'+#user.userId", condition = "#result > 0")
    public int updateUserById(User user) {
        return userDao.updateUserById(user);
    }


    /**
     * 根据用户id禁用用户
     * @param userId 用户id
     * @return int
     */
    @CacheEvict(value="user", key="'user_'+#userId", condition = "#result > 0")
    @Override
    public int banUser(int userId){
        User user = new User();
        user.setUserId(userId);
        user.setDisabled(true);
        return userDao.updateUserById(user);

    }
}
