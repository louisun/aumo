package com.louisun.util.constant;

public enum SuccessEnum {
    S_200("200", "Success");

    private String successCode;
    private String successMessage;

    SuccessEnum() {
    }

    SuccessEnum(String successCode, String successMessage) {
        this.successCode = successCode;
        this.successMessage = successMessage;
    }

    public String getSuccessCode() {
        return successCode;
    }

    public String getSuccessMessage() {
        return successMessage;
    }
}
