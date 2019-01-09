package com.louisun.controller;

import com.alibaba.fastjson.JSONObject;
import com.louisun.model.User;
import com.louisun.service.UserService;
import com.louisun.util.JsonResult;
import com.louisun.util.constant.ErrorEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
public class UserController {
    @Autowired
    private UserService userService = null;

    /** 注册用户 */
    @PostMapping("/register")
    public JSONObject createUser(@RequestBody User user) {
        SimpleHash simpleHash = new SimpleHash("MD5", user.getPassword(), null, 2);
        user.setPassword(simpleHash.toHex());
        return userService.insertUser(user);
    }

    /** 通过 email 获取用户信息 */
    @GetMapping("/userinfo")
    public JSONObject getUserByEmail(@SessionAttribute("email") String email){
        return userService.getUserBasicInfoByEmail(email);

    }

    /** 更新用户信息：基本信息、密码 */
    @PostMapping("/userupdate")
    public JSONObject updateUser(@RequestBody JSONObject requestBody) {

        User user = new User();
        if(requestBody.getString("type").equals("userInfo")){
            user.setEmail(requestBody.getString("email"));
            user.setNickname(requestBody.getString("nickname"));
            user.setMoto(requestBody.getString("moto"));
            return userService.updateUserByEmail(user);

        }
        else if(requestBody.getString("type").equals("userPassword")){
            JSONObject result = userService.getUserByEmail(requestBody.getString("email"));
            if(result.getString("returnCode").equals("200")){
                User u = (User) result.get("returnData");
                SimpleHash simpleHash1 = new SimpleHash("MD5", requestBody.getString("currentPassword"), null, 2);
                // 旧密码匹配，设置新密码
                if(u.getPassword().equals(simpleHash1.toHex())){
                    SimpleHash simpleHash2 = new SimpleHash("MD5", requestBody.getString("newPassword"), null, 2);
                    user.setEmail(requestBody.getString("email"));
                    user.setPassword(simpleHash2.toHex());
                    return userService.updateUserByEmail(user);
                } else return JsonResult.errorResult(ErrorEnum.E_3003);

            }
            else return JsonResult.errorResult(ErrorEnum.E_3001);
        }
        return JsonResult.errorResult(ErrorEnum.E_3004);
    }

    /** 通过 id 获取用户信息 */
    @GetMapping("/user/{id}")
    public JSONObject getUserById(@PathVariable("id") int id) {
        return userService.getUserById(id);
    }


    /** 通过 id 删除用户 */
    @PostMapping("/user/{id}")
    public JSONObject deleteUser(@PathVariable("id") int id) {
        return userService.deleteUserById(id);
    }



}
