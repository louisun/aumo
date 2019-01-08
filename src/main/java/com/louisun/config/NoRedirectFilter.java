package com.louisun.config;

import com.alibaba.fastjson.JSONObject;
import com.louisun.util.constant.ErrorEnum;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

// 认证失败不要重定向到登录页面：返回失败 json
public class NoRedirectFilter extends FormAuthenticationFilter {
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
