package com.linqi.userservice.common.enums;

import lombok.Getter;

@Getter
public enum RoleResultEnum implements BaseEnum {

    ROLE_NAME_EXIST(40001,"角色名称已经存在")
    ;
    private Integer code;
    private String msg;

    RoleResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
