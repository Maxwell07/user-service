package com.linqi.userservice.common.exception;

import lombok.Getter;
import com.linqi.userservice.common.enums.BaseEnum;

@Getter
public class ParamException extends RuntimeException {
    private Integer code;

    public ParamException(Integer code,String message) {
        super(message);
        this.code = code;
    }

    public ParamException(BaseEnum baseEnum) {
        this(baseEnum.getCode(), baseEnum.getMsg());
    }
}
