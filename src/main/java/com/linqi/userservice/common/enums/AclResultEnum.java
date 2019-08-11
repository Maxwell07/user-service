package com.linqi.userservice.common.enums;

import lombok.Getter;

@Getter
public enum AclResultEnum implements BaseEnum {

    HAS_SAME_CHILD_ACL(30001, "当前权限模块下面存在相同名称的权限点"),
    ;
    private Integer code;
    private String msg;

    AclResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
