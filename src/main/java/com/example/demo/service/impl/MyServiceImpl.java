package com.example.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.service.MyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by finup on 2017/8/7.
 */
@Service
public class MyServiceImpl implements MyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyServiceImpl.class);

    @Override
    public String getFromService(Long id){
        return "hello:"+id;
    }

    @Override
    public JSONObject addCookie(HttpServletRequest request, HttpServletResponse response, String cookieName){
        LOGGER.info("cookieName:{}", cookieName);
        JSONObject res = new JSONObject();
        res.put("code", 0);
        Cookie cookie = new Cookie(cookieName,"2333");
        // cooKie 一定要设置path 如果controller没用@ResponeseBody 则会默认path 为:/
        cookie.setPath("/");
        response.addCookie(cookie);
        return res;
    }

    @Override
    public JSONObject getCookie(HttpServletRequest request, HttpServletResponse response, String cookieName){
        LOGGER.info("cookieName:{}", cookieName);
        JSONObject res = new JSONObject();
        res.put("code", 0);
        Cookie[] cookies = request.getCookies(); //获取cookie数组
        for (Cookie item:cookies) {
            LOGGER.info(item.getName());
            LOGGER.info(item.getValue());
        }
        return res;
    }
}
