package com.spider.enums;

import org.springframework.util.StringUtils;

public enum ProxyType {
    DEFAULT("default"),  NONE("none"), CUSTOME("custome");

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    ProxyType(String value) {
        this.value = value;
    }

    public static ProxyType parse(String type) {
        if (StringUtils.isEmpty(type)) {
            return DEFAULT;
        }
        for (ProxyType proxyType : values()) {
            if (proxyType.getValue().equals(type)) {
                return proxyType;
            }
        }
        return DEFAULT;
    }


}
