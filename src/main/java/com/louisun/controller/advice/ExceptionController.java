package com.louisun.controller.advice;

import com.alibaba.fastjson.JSONObject;
import com.louisun.util.JsonResult;
import com.louisun.util.constant.ErrorEnum;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value= AuthorizationException.class)
    @ResponseBody
    public JSONObject permissionErrorHandler(){
        return JsonResult.errorResult(ErrorEnum.E_0001);
    }
}
