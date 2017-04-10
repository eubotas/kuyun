package com.kuyun.demo.rpc.service.impl;

import com.kuyun.demo.rpc.api.DemoService;

/**
 * 实现DemoService接口
 * Created by kuyun on 2017/4/1.
 */
public class DemoServiceImpl implements DemoService {

    @Override
    public String sayHello(String name) {
        return "hello " + name;
    }

}