package com.louisun.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class User {
    /**
     * 用户 ID
     */
    private Integer userId;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 角色 ID
     */
    private Integer roleId;

    /**
     * 个性签名
     */
    private String moto;

    /**
     * 头像路径
     */
    private String avatarPath;

    /**
     * 是否被禁用
     */
    private Boolean disabled;

    /**
     * 用户创建时间
     */
    private Date createTime;

    /**
     * 用户更新时间
     */
    private Date updateTime;
}