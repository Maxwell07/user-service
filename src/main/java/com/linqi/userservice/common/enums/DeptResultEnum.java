package com.linqi.userservice.common.enums;

import lombok.Getter;

@Getter
public enum DeptResultEnum implements BaseEnum{
    EXIST_SAME_DEPT(20001, "同一层级下存在相同名称的部门"),
    HAS_CHILD_DEPT_CANT_DELETE(20002, "当前模块下面有子模块，无法删除"),
    ;
    private Integer code;
    private String msg;

    DeptResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
