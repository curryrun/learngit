package com.example.demo.test;

import com.example.demo.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by finup on 2017/8/4.
 */
@Controller
//@RequestMapping("/")
public class TransController {

    @Autowired
    private MyService myService;

    @RequestMapping("/index")
//    @ResponseBody
    public String getChannel(HttpServletRequest request, HttpServletResponse response){

        System.out.println("come!");
        myService.addCookie(request, response, "cookie1");
        return "/index";
    }
}
