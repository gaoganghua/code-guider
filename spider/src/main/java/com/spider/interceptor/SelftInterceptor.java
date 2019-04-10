package com.spider.interceptor;

import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.protocol.HttpContext;

public class SelftInterceptor implements HttpRequestInterceptor, HttpResponseInterceptor {

    private BaseInterceptor spiderInterceptor;

    public void setSpiderInterceptor(BaseInterceptor spiderInterceptor) {
        this.spiderInterceptor = spiderInterceptor;
    }

    @Override
    public void process(HttpRequest httpRequest, HttpContext httpContext) {
        spiderInterceptor.preExecute();
    }

    @Override
    public void process(HttpResponse httpResponse, HttpContext httpContext) {
        spiderInterceptor.postExecute();
    }
}