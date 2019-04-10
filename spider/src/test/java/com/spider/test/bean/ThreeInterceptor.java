package com.spider.test.bean;

import com.spider.interceptor.BaseInterceptor;

public class ThreeInterceptor extends BaseInterceptor {
    @Override
    public void preTask() {
        System.out.println("three pre...");
    }

    @Override
    public void postTask() {
        System.out.println("three post...");

    }
}
