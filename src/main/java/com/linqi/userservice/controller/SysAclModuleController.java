package com.linqi.userservice.controller;

import com.linqi.userservice.common.response.ResultVO;
import com.linqi.userservice.common.response.ResultVOUtil;
import com.linqi.userservice.param.AclModuleParam;
import com.linqi.userservice.service.SysAclModuleService;
import com.linqi.userservice.service.SysTreeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/sys/aclModule")
@Slf4j
public class SysAclModuleController {

    @Resource
    private SysAclModuleService sysAclModuleService;
    @Resource
    private SysTreeService sysTreeService;

    // @RequestMapping("/acl.page")
    // public ModelAndView page() {
    //     return new ModelAndView("acl");
    // }

    @PostMapping("/save")
    public ResultVO saveAclModule(@Valid @RequestBody AclModuleParam param) {
        sysAclModuleService.save(param);
        return ResultVOUtil.success();
    }

    @PostMapping("/update")
    public ResultVO updateAclModule(@Valid @RequestBody AclModuleParam param) {
        sysAclModuleService.update(param);
        return ResultVOUtil.success();
    }

    @PostMapping("/tree")
    public ResultVO tree() {
        return ResultVOUtil.success(sysTreeService.aclModuleTree());
    }

    @PostMapping("/delete")
    public ResultVO delete(@RequestParam("id") int id) {
        sysAclModuleService.delete(id);
        return ResultVOUtil.success();
    }
}
