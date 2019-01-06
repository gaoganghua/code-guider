package com.spider.interceptor;

import com.spider.interfaces.Chain;
import com.spider.interfaces.SpiderInterceptor;
import org.apache.http.*;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;

public class SelftInterceptor implements HttpRequestInterceptor, HttpResponseInterceptor {

    private SpiderInterceptor spiderInterceptor;

    public void setSpiderInterceptor(SpiderInterceptor spiderInterceptor) {
        this.spiderInterceptor = spiderInterceptor;
    }

    @Override
    public void process(HttpRequest httpRequest, HttpContext httpContext) throws HttpException, IOException {
        spiderInterceptor.preTask();
    }

    @Override
    public void process(HttpResponse httpResponse, HttpContext httpContext) throws HttpException, IOException{
        spiderInterceptor.postTask();
    }
}