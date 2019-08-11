package com.linqi.userservice.common.response;

import com.linqi.userservice.common.enums.BaseEnum;
import com.linqi.userservice.common.enums.CommonResultEnum;

public class ResultVOUtil {
    public static <T> ResultVO success(T data) {
        ResultVO<T> resultVO = new ResultVO<>();
        resultVO.setCode(CommonResultEnum.SUCCESS.getCode());
        resultVO.setMsg(CommonResultEnum.SUCCESS.getMsg());
        resultVO.setData(data);
        return resultVO;
    }

    public static ResultVO success() {
       return success(null);
    }

    public static ResultVO error(BaseEnum baseEnum) {
        return error(baseEnum.getCode(), baseEnum.getMsg());
    }

    public static ResultVO error(Integer code, String msg) {
        ResultVO<Object> resultVO = new ResultVO<>();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }
}
