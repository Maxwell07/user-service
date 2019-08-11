package com.linqi.userservice.controller;

import com.google.common.collect.Maps;
import com.linqi.userservice.common.beans.PageQuery;
import com.linqi.userservice.common.beans.PageResult;
import com.linqi.userservice.common.response.ResultVO;
import com.linqi.userservice.common.response.ResultVOUtil;
import com.linqi.userservice.model.SysUser;
import com.linqi.userservice.param.UserParam;
import com.linqi.userservice.service.SysRoleService;
import com.linqi.userservice.service.SysTreeService;
import com.linqi.userservice.service.SysUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/sys/user")
public class SysUserController {

    @Resource
    private SysUserService sysUserService;
    @Resource
    private SysTreeService sysTreeService;
    @Resource
    private SysRoleService sysRoleService;

    // @RequestMapping("/noAuth.page")
    // public ModelAndView noAuth() {
    //     return new ModelAndView("noAuth");
    // }

    @PostMapping("/save")
    public ResultVO saveUser(@Valid @RequestBody UserParam param) {
        sysUserService.save(param);
        return ResultVOUtil.success();
    }

    @PostMapping("/update")
    public ResultVO updateUser(UserParam param) {
        sysUserService.update(param);
        return ResultVOUtil.success();
    }

    @PostMapping("/page")
    public ResultVO page(@RequestParam("deptId") int deptId, PageQuery pageQuery) {
        PageResult<SysUser> result = sysUserService.getPageByDeptId(deptId, pageQuery);
        return ResultVOUtil.success(result);
    }

    @PostMapping("/acls")
    public ResultVO acls(@RequestParam("userId") int userId) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("acls", sysTreeService.userAclTree(userId));
        map.put("roles", sysRoleService.getRoleListByUserId(userId));
        return ResultVOUtil.success(map);
    }
}
