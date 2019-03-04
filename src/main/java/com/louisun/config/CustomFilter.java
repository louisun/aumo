package com.louisun.config;

import com.alibaba.fastjson.JSONObject;
import com.louisun.util.constant.ErrorEnum;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CustomFilter extends UserFilter {
    /**
     * 使用了Shiro 框架，前端请求有 2 次，第一次为 Options 不带 cookie，会被拦截失效，无法完成第二次请求
     * 允许所有 OPTIONS 请求通过
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        if (httpRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            setHeader(httpRequest,httpResponse);
            return true;
        }
        return super.preHandle(request,response);
    }


    /**
     * 工具函数，为上述 Options 请求的响应头设置header，实现跨域
     */
    private void setHeader(HttpServletRequest request,HttpServletResponse response){
        //跨域的header设置
        response.setHeader("Access-control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", request.getMethod());
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
        //防止乱码，适用于传输JSON数据
        response.setHeader("Content-Type","application/json;charset=UTF-8");
        response.setStatus(HttpStatus.OK.value());
    }



    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("returnCode", ErrorEnum.E_2001.getErrorCode());
        jsonObject.put("returnMsg", ErrorEnum.E_2001.getErrorMessage());
        // 将这个 json 消息写到响应头去
        PrintWriter out = null;
        HttpServletResponse res = (HttpServletResponse) response;
        try{
            res.setCharacterEncoding("UTF-8");
            res.setContentType("application/json");
            // 写入 response 的 Content（即我们的 JSON 错误信息）
            out = response.getWriter();
            out.println(jsonObject);
        }catch (Exception e){
        }finally {
            if(out != null){
                out.flush();
                out.close();
            }
        }
        return false;
    }

/*
    // 注册一个过滤器
    @Bean
    public FilterRegistrationBean registration(NoRedirectFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean(filter);
        registration.setEnabled(false);
        return registration;
    }
*/
}
