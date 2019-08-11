package com.linqi.userservice.controller;

import com.alibaba.fastjson.JSON;
import com.linqi.userservice.common.exception.ParamException;
import com.linqi.userservice.common.exception.PermissionException;
import com.linqi.userservice.common.response.ResultVO;
import com.linqi.userservice.common.response.ResultVOUtil;
import com.linqi.userservice.common.utils.ApplicationContextHelper;
import com.linqi.userservice.dao.SysAclModuleMapper;
import com.linqi.userservice.model.SysAclModule;
import com.linqi.userservice.param.TestVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @RequestMapping("/hello.json")
    @ResponseBody
    public ResultVO hello() {
        log.info("hello");
        throw new PermissionException("test exception");
        // return JsonData.success("hello, permission");
    }

    @RequestMapping("/validate.json")
    @ResponseBody
    public ResultVO validate(TestVo vo) throws ParamException {
        log.info("validate");
        SysAclModuleMapper moduleMapper = ApplicationContextHelper.popBean(SysAclModuleMapper.class);
        SysAclModule module = moduleMapper.selectByPrimaryKey(1);
        log.info(JSON.toJSONString(module));
        return ResultVOUtil.success("test validate");
    }
}
