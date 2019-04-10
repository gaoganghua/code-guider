package com.spider.test.bean;

import com.spider.interceptor.BaseInterceptor;

public class SecondInterceptor extends BaseInterceptor {
    @Override
    public void preTask() {
        System.out.println("second pre....");
    }

    @Override
    public void postTask() {
        System.out.println("second post.....");
    }
}
