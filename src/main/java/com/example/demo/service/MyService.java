package com.example.demo.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by finup on 2017/8/7.
 */
public interface MyService {

    public String getFromService(Long id);

    public JSONObject addCookie(HttpServletRequest request, HttpServletResponse response, String cookieName);
}
