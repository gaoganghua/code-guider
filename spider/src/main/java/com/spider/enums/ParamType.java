package com.spider.enums;

import org.springframework.util.StringUtils;

public enum ParamType {
    STRING("string"), FILE("file"), UELENCODEFORM("urlencodeform"), BYTEARRAY("bytearray"), INPUTSTREAM("inputstream"), MULTIPART("multipart");

    private String value;

    ParamType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static ParamType parse(String type) {
        if (StringUtils.isEmpty(type)) {
            return STRING;
        }
        for (ParamType paramType : values()) {
            if (paramType.getValue().equals(type)) {
                return paramType;
            }
        }
        return STRING;
    }
}
