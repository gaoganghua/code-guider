package com.spider.bean;

import com.spider.interfaces.SpiderHandler;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.cookie.BasicClientCookie;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SelfResponseHandler implements ResponseHandler<WebResponse> {
    private SpiderHandler handler;

    @Override
    public WebResponse handleResponse(HttpResponse httpResponse) throws IOException {
        WebResponse response = new WebResponse();

        response.setStatusLine(httpResponse.getStatusLine());
        response.setStateCode(httpResponse.getStatusLine().getStatusCode());
        response.setEntity(new BufferedHttpEntity(httpResponse.getEntity()));
        response.setCookie(processCookie(httpResponse));
        response.setHeaders(processHeader(httpResponse));

        return response;
    }

    private Map<String, String> processHeader(HttpResponse response) {
        Map<String, String> headers = new HashMap<>();

        if (response.getAllHeaders() != null) {
            for (Header header : response.getAllHeaders()) {
                headers.put(header.getName(), header.getValue());
            }
        }
        return headers;
    }

    private List<Cookie> processCookie(HttpResponse response) {
        List<Cookie> cookies = new LinkedList<>();

        if (response.getHeaders("set-Cookie") != null) {
            for (Header header : response.getHeaders("set-Cookie")) {
                cookies.add(new BasicClientCookie(header.getName(), header.getValue()));
            }
        }
        return cookies;
    }
}
