package com.spider.test.bean;

import com.spider.interceptor.BaseInterceptor;

public class FirstInterceptor extends BaseInterceptor {
    @Override
    public void preTask() {
        System.out.println("first pre...");
    }

    @Override
    public void postTask() {
        System.out.println("first post....");
    }
}
