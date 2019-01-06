package com.spider.bean;

import com.spider.interfaces.Constants;
import com.spider.util.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.cookie.Cookie;
import org.springframework.util.StringUtils;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class WebResponse {
    private int stateCode;

    private StatusLine statusLine;

    private List<Cookie> cookie;

    private Map<String, String> headers;

    private HttpEntity entity;

    private String body;

    private String charset;

    public int getStateCode() {
        return stateCode;
    }

    public void setStateCode(int stateCode) {
        this.stateCode = stateCode;
    }

    public StatusLine getStatusLine() {
        return statusLine;
    }

    public void setStatusLine(StatusLine statusLine) {
        this.statusLine = statusLine;
    }

    public List<Cookie> getCookie() {
        return cookie;
    }

    public void setCookie(List<Cookie> cookie) {
        this.cookie = cookie;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public HttpEntity getEntity() {
        return entity;
    }

    public void setEntity(HttpEntity entity) {
        this.entity = entity;
    }

    public String getBody() {
        if (!StringUtils.isEmpty(body)) {
            return body;
        }
        try {
            body = IOUtils.parseStream(this.entity.getContent(), Constants.DEFAULT_CHARSET);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }
}
