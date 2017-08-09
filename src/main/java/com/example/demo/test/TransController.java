package com.example.demo.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by finup on 2017/8/4.
 */
@Controller
//@RequestMapping("/")
public class TransController {

    @RequestMapping("/index")
//    @ResponseBody
    public String getChannel(){

        System.out.println("come!");
        return "/index";
    }
}
