package com.site.mountain.config;


import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

public class MySessionManager extends DefaultWebSessionManager {
    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        String token = httpServletRequest.getHeader("token");
        System.out.println("token：" + token);
        if (token == null) {
            Cookie[] cookies = httpServletRequest.getCookies();
            if (cookies != null) {
                Cookie cookie = null;
                for (int i = 0; i < cookies.length; i++) {
                    cookie = cookies[i];
                    String val = cookie.getValue();
                    val = val.replace("%22", "\"").replace("%2C",",");
                    JSONObject jsonObject = JSONObject.parseObject(val);
                    token = jsonObject.getString("accessToken");
                }
            }
        }
        String uri = httpServletRequest.getRequestURI();

        if (!StringUtils.isEmpty(token)) {
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, "token");
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, token);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            return token;
        } else {
            return super.getSessionId(request, response);
        }
    }
}