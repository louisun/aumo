package com.louisun.util.constant;

import com.alibaba.druid.wall.violation.ErrorCode;

public enum ErrorEnum {
    /**
     * 创建用户相关的错误码
     */
    E_1001("1001", "创建用户失败"),
    E_1002("1002", "用户已存在"),
    E_1003("1003", "用户不存在"),

    /**
     * 登录、登出相关的错误码
     */
    E_2001("2001", "登录失败"),
    E_2002("2002", "登出失败"),

    /**
     * 修改个人信息相关的错误码
     */
    E_3001("3001", "修改信息失败"),
    E_3002("3002", "修改用户信息失败"),
    E_3003("3003", "修改密码失败，当前密码输入不正确"),
    E_3004("3004", "修改信息失败，类型不正确"),

    /**
     * 与帖子相关的错误码
     */
    E_4001("4001","保存帖子失败"),
    E_4002("4002","获取帖子失败"),
    E_4003("4003","修改帖子失败"),

    /**
     * 与版块相关的错误码
     */
    E_5001("5001","保存版块失败"),
    E_5002("5002","获取版块失败"),
    E_5003("5003","删除版块失败"),

    /**
     * 与评论相关的错误码
     */
    E_6001("6001","保存评论失败"),
    E_6002("6002","获取评论失败"),
    E_6003("6003","删除评论失败");

//    E_5002("5002","获取文章评论失败"),
//    E_5003("5003","获取用户所属评论失败"),
//    E_5004("5004","删除用户评论失败");




    private String errorCode;
    private String errorMessage;

    ErrorEnum(){}
    ErrorEnum(String errorCode, String errorMessage){
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
