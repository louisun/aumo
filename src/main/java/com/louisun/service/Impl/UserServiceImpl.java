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
    @Autowired
    private UserDao userMapper = null;

    @Override
    public JSONObject getUserById(int id) {
        User user = userMapper.selectUserById(id);
        if(user!=null) return JsonResult.successResult(user);
        else return JsonResult.errorResult(ErrorEnum.E_1003); // 用户不存在
    }

    @Override
    public JSONObject getUserByEmail(String email) {
        User user = userMapper.selectUserByEmail(email);
        if(user!=null) return JsonResult.successResult(user);
        else return JsonResult.errorResult(ErrorEnum.E_1003); // 用户不存在
    }

    @Override
    public JSONObject insertUser(User user) {
        if (userMapper.selectUserByEmail(user.getEmail()) != null) {
            // 用户已存在
            return JsonResult.errorResult(ErrorEnum.E_1002);
        }

        if (userMapper.insertUser(user) == 0) {
            // 其他原因导致没有插入，创建用户失败
            return JsonResult.errorResult(ErrorEnum.E_1001);
        }
        else{
            return JsonResult.successResult(user);
        }
    }

    @Override
    public JSONObject deleteUserById(int id) {
        User user = userMapper.selectUserById(id);
        if(user!=null) {
            userMapper.deleteByUserId(id);
            return JsonResult.successResult("删除成功");
        }
        else return JsonResult.errorResult(ErrorEnum.E_1003); // 用户不存在

    }

    @Override
    public JSONObject updateUserById(User user) {
        userMapper.updateUserById(user);
        return JsonResult.successResult("更新成功");
    }

    public JSONObject updateUserByEmail(User user){
        userMapper.updateUserByEmail(user);
        return JsonResult.successResult("更新成功");
    }
}
