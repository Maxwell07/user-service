package com.linqi.userservice.common.enums;

import lombok.Getter;

@Getter
public enum UserResultEnum implements BaseEnum {

    TELEPHONE_EXIST(40001, "电话已被占用"),
    EMAIL_EXIST(40002, "邮箱已被占用"),
    ;
    private Integer code;
    private String msg;

    UserResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
