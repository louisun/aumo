package com.louisun.util;

import com.alibaba.fastjson.JSONObject;
import com.louisun.util.constant.ErrorEnum;
import com.louisun.util.constant.SuccessEnum;

public class JsonResult {

    /**
     * returnData为空的成功消息json
     **/
    public static JSONObject successResult(){
        return successResult(new JSONObject());
    }

    /**
     * 带返回数据的成功消息 JSON
     **/
    public static JSONObject successResult(Object returnData){
        JSONObject resultJson = new JSONObject();
        resultJson.put("returnCode", SuccessEnum.S_200.getSuccessCode());
        // successResult 的 returnMsg 并不重要
        resultJson.put("returnMsg", SuccessEnum.S_200.getSuccessMessage());
        resultJson.put("returnData", returnData);
        return resultJson;
    }

    /**
     * returnData为空，带特定returnMsg的的失败消息
     **/
    public static JSONObject errorResult(ErrorEnum errorEnum){
        JSONObject resultJson = new JSONObject();
        resultJson.put("returnCode", errorEnum.getErrorCode());
        resultJson.put("returnMsg", errorEnum.getErrorMessage());
        // errorResult 的 returnData 并不重要
        resultJson.put("returnData", new JSONObject());
        return resultJson;
    }
}
