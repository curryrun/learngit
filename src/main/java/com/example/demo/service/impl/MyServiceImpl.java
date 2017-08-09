package com.example.demo.service.impl;

import com.example.demo.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by finup on 2017/8/7.
 */
@Service
public class MyServiceImpl implements MyService {

    @Override
    public String getFromService(Long id){
        return "hello:"+id;
    }
}
