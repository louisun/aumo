package com.louisun.util.constant;

import com.alibaba.druid.wall.violation.ErrorCode;

public enum ErrorEnum {
    E_1001("1001", "创建用户失败"),
    E_1002("1002", "用户已存在"),

    E_1003("1003", "用户不存在"),

    E_2001("2001", "登录失败"),

    E_3001("3001", "修改用户信息失败"),
    E_3002("3002", "修改密码失败，当前密码输入不正确");


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
