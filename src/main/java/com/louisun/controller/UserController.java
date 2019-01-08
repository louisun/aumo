package com.louisun.controller;

import com.alibaba.fastjson.JSONObject;
import com.louisun.model.User;
import com.louisun.service.UserService;
import com.louisun.util.JsonResult;
import com.louisun.util.constant.ErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.apache.shiro.crypto.hash.SimpleHash;


@RestController
public class UserController {
    @Autowired
    private UserService userService = null;

    // 注册用户
    @PostMapping("/register")
    public JSONObject createUser(@RequestBody User user) {
        SimpleHash simpleHash = new SimpleHash("MD5", user.getPassword(), null, 2);
        user.setPassword(simpleHash.toHex());
        return userService.insertUser(user);
    }



    // 通过 id 获取用户信息
    @GetMapping("/user/{id}")
    public JSONObject getUserByUserId(@PathVariable("id") int id) {
        return userService.getUserById(id);
    }

    // 通过 id 删除用户
    @PostMapping("/user/{id}")
    public JSONObject deleteUser(@PathVariable("id") int id) {
        return userService.deleteUserById(id);
    }

    // 更新用户（填写任意字段）
    @PostMapping("/user/update")
    public JSONObject updateUser(@RequestBody User user) {
        if (user.getPassword() == null) { // 只修改昵称、个性签名
            return userService.updateUserByEmail(user);
        } else {
            // 修改密码
            User u = (User) userService.getUserByEmail(user.getEmail()).get("returnData");
            if (user.getPassword() == u.getPassword()) {
                return userService.updateUserByEmail(user);
            } else {
                // 当前密码不正确
                return JsonResult.errorResult(ErrorEnum.E_3002);
            }
        }
    }

}
