package com.linqi.userservice.controller;

import com.google.common.collect.Maps;
import com.linqi.userservice.common.beans.PageQuery;
import com.linqi.userservice.common.response.ResultVO;
import com.linqi.userservice.common.response.ResultVOUtil;
import com.linqi.userservice.model.SysRole;
import com.linqi.userservice.param.AclParam;
import com.linqi.userservice.service.SysAclService;
import com.linqi.userservice.service.SysRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sys/acl")
@Slf4j
public class SysAclController {

    @Autowired
    private SysAclService sysAclService;

    @Autowired
    private SysRoleService sysRoleService;

    @PostMapping("/save")
    public ResultVO saveAclModule(@Valid @RequestBody AclParam param) {
        sysAclService.save(param);
        return ResultVOUtil.success();
    }

    @PostMapping("/update")
    public ResultVO updateAclModule(@Valid @RequestBody AclParam param) {
        sysAclService.update(param);
        return ResultVOUtil.success();
    }

    @PostMapping("/page")
    public ResultVO list(@RequestParam("aclModuleId") Integer aclModuleId, PageQuery pageQuery) {
        return ResultVOUtil.success(sysAclService.getPageByAclModuleId(aclModuleId, pageQuery));
    }

    @PostMapping("acls")
    public ResultVO acls(@RequestParam("aclId") int aclId) {
        Map<String, Object> map = Maps.newHashMap();
        List<SysRole> roleList = sysRoleService.getRoleListByAclId(aclId);
        map.put("roles", roleList);
        map.put("users", sysRoleService.getUserListByRoleList(roleList));
        return ResultVOUtil.success(map);
    }
}
