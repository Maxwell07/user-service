package com.linqi.userservice.common.enums;

import lombok.Getter;

@Getter
public enum CommonResultEnum implements BaseEnum{
    SUCCESS(0, "请求成功"),
    SERVER_ERROR(10001, "服务器异常"),
    REQUEST_PARAM_ERROR(10010, "请求参数错误"),

    ;
    private Integer code;
    private String msg;

    CommonResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
