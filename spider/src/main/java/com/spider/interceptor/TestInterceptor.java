package com.spider.interceptor;

import com.spider.interfaces.Chain;
import com.spider.interfaces.SpiderInterceptor;

public class TestInterceptor implements SpiderInterceptor {
    @Override
    public void preTask() {
        System.out.println("pre task....");
    }

    @Override
    public void postTask() {
        System.out.println("post task.....");
    }
}
