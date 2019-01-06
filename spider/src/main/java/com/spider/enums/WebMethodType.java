package com.spider.enums;

import org.springframework.util.StringUtils;

public enum WebMethodType {
    PUT("put"), DELETE("delete"), POST("post"), GET("get"), HEAD("head"), OPTIONS("options");

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    WebMethodType(String value) {
        this.value = value;
    }

    public static WebMethodType parse(String method) {
        if (StringUtils.isEmpty(method)) {
            return GET;
        }
        for (WebMethodType webMethod : values()) {
            if (webMethod.getValue().equals(method)) {
                return webMethod;
            }
        }
        return GET;

    }
}
