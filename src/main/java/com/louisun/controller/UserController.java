package com.louisun.controller;

import com.alibaba.fastjson.JSONObject;
import com.louisun.model.User;
import com.louisun.service.UserService;
import com.louisun.util.JsonResult;
import com.louisun.util.constant.ErrorEnum;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


@RestController
@Slf4j
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 注册用户 /user POST
     *
     * @param user 请求体转换的User对象：包含邮箱、密码、昵称
     * @return JSONObject
     */
    @ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
    @PostMapping("/user")
    public JSONObject addUser(@RequestBody User user) {
        SimpleHash simpleHash = new SimpleHash("MD5", user.getPassword(), null, 2);
        user.setPassword(simpleHash.toHex());
        int result = userService.insertUser(user);
        return (result > 0) ? JsonResult.successResult(user) : JsonResult.errorResult(ErrorEnum.E_1001);
    }

    /**
     * 根据用户id获取用户信息 /user/{id} GET
     *
     * @param userId 用户 id
     * @return JSONObject
     */
    @ApiOperation(value = "获取用户信息", notes = "根据用户id获取用户信息")
    @GetMapping("/user/{id}")
    public JSONObject getUser(@PathVariable("id") int userId) {
        User user = userService.getUserById(userId);
        if (user != null) return JsonResult.successResult(user);
        else return JsonResult.errorResult(ErrorEnum.E_1003); // 用户不存在
    }

    /**
     * 获取当前用户的信息 /user GET
     *
     * @param userId 当前 session 中保存的用户id
     * @return JSONObject
     */
    @GetMapping("/user")
    public JSONObject getCurrentUser(@SessionAttribute("userId") int userId) {
        User user = userService.getUserById(userId);
        if (user != null) return JsonResult.successResult(user);
        else return JsonResult.errorResult(ErrorEnum.E_1003); // 用户不存在
    }


    /**
     * 更新当前用户的部分信息
     *
     * @param userId        当前 session 中保存的用户id
     * @param updateRequest 请求更新的信息
     * @param type          更新类型: basic（昵称、个性签名），password（密码）
     * @return JSONObject
     */
    @PatchMapping("/user")
    public JSONObject updateUser(@SessionAttribute("userId") int userId, @RequestBody JSONObject updateRequest, @RequestParam(value = "type") String type) {

        User user = new User();
        user.setUserId(userId);
        if (type.equals("basic")) {
            // 修改昵称和个性签名
            user.setNickname(updateRequest.getString("nickname"));
            user.setMoto(updateRequest.getString("moto"));
            int result = userService.updateUserById(user);
            return (result > 0) ? JsonResult.successResult("更新成功") : JsonResult.errorResult(ErrorEnum.E_3002);
        } else if (type.equals("password")) {
            // 修改密码：要先匹配旧密码，匹配成功才能设置新密码
            User u = userService.getUserById(userId);

            if (u != null) {
                SimpleHash simpleHash1 = new SimpleHash("MD5", updateRequest.getString("currentPassword"), null, 2);
                // 旧密码匹配成功，设置新密码
                if (u.getPassword().equals(simpleHash1.toHex())) {
                    SimpleHash simpleHash2 = new SimpleHash("MD5", updateRequest.getString("newPassword"), null, 2);
                    user.setPassword(simpleHash2.toHex());
                    int result = userService.updateUserById(user);
                    return (result > 0) ? JsonResult.successResult("更新成功") : JsonResult.errorResult(ErrorEnum.E_3002);
                } else return JsonResult.errorResult(ErrorEnum.E_3003);
            } else return JsonResult.errorResult(ErrorEnum.E_3001);
        }
        return JsonResult.errorResult(ErrorEnum.E_3004);
    }

    /**
     * 设置当前用户头像
     *
     * @param userId        当前 session 中保存的用户id
     * @param uploadPicture 用户上传的图片
     * @return JSONObject
     */
    @PostMapping("/avatar")
    public JSONObject uploadAvatar(@SessionAttribute("userId") int userId, MultipartFile uploadPicture) {
        File dest = new File(userId + ".jpg");
        try {
            uploadPicture.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
            return JsonResult.errorResult(ErrorEnum.E_3002);
        }
        return JsonResult.successResult("上传成功");
    }
}
