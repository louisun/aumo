package com.louisun.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.louisun.dao.UserDao;
import com.louisun.model.User;
import com.louisun.service.UserService;
import com.louisun.util.JsonResult;
import com.louisun.util.constant.ErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * 根据用户id得到用户对象
     * @param id  用户id
     * @return JSONObject
     */
    @Override
    public JSONObject getUserById(int id) {
        User user = userDao.selectUserById(id);
        if(user!=null){
            return JsonResult.successResult(user);
        }
        else return JsonResult.errorResult(ErrorEnum.E_1003); // 用户不存在
    }

    /**
     * 根据用户邮箱更新用户信息
     * @param email 邮箱名
     * @return JSONObject
     */
    @Override
    public JSONObject getUserByEmail(String email) {
        User user = userDao.selectUserByEmail(email);
        if(user!=null){
            return JsonResult.successResult(user);
        }
        else return JsonResult.errorResult(ErrorEnum.E_1003); // 用户不存在
    }


    /**
     * 新增用户
     * @param user 用户对象
     * @return JSONObject
     */
    @Override
    public JSONObject insertUser(User user) {
        if (userDao.selectUserByEmail(user.getEmail()) != null) {
            // 用户已存在
            return JsonResult.errorResult(ErrorEnum.E_1002);
        }

        if (userDao.insertUser(user) == 0) {
            // 其他原因导致没有插入，创建用户失败
            return JsonResult.errorResult(ErrorEnum.E_1001);
        }
        else{
            return JsonResult.successResult(user);
        }
    }

    /**
     * 根据用户id删除用户
     * @param id 用户 id
     * @return JSONObject
     */
    @Override
    public JSONObject deleteUserById(int id) {
        User user = userDao.selectUserById(id);
        if(user!=null) {
            userDao.deleteByUserId(id);
            return JsonResult.successResult("删除成功");
        }
        else return JsonResult.errorResult(ErrorEnum.E_1003); // 用户不存在

    }

    /**
     * 根据用户id更新用户信息
     * @param user 用户对象
     * @return JSONObject
     */
    @Override
    public JSONObject updateUserById(User user) {
        userDao.updateUserById(user);
        return JsonResult.successResult("更新成功");
    }

    /**
     * 根据用户邮箱更新用户信息
     * @param user 用户对象
     * @return JSONObject
     */
    public JSONObject updateUserByEmail(User user){
        userDao.updateUserByEmail(user);
        return JsonResult.successResult("更新成功");
    }
}
