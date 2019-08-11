package com.linqi.userservice.common.enums;

import lombok.Getter;

@Getter
public enum LogResultEnum implements BaseEnum {

    HAS_SAME_CHILD_ACL(90001, "传入的日期格式有问题，正确格式为：yyyy-MM-dd HH:mm:ss"),
    CREATE_DELETE_NOT_RESET(90020, "新增和删除操作不做还原"),
    ;
    private Integer code;
    private String msg;

    LogResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
