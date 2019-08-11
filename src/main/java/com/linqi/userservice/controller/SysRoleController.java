package com.linqi.userservice.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.linqi.userservice.common.response.ResultVO;
import com.linqi.userservice.common.response.ResultVOUtil;
import com.linqi.userservice.common.utils.StringUtil;
import com.linqi.userservice.model.SysUser;
import com.linqi.userservice.param.RoleParam;
import com.linqi.userservice.service.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sys/role")
public class SysRoleController {

    @Resource
    private SysRoleService sysRoleService;
    @Resource
    private SysTreeService sysTreeService;
    @Resource
    private SysRoleAclService sysRoleAclService;
    @Resource
    private SysRoleUserService sysRoleUserService;
    @Resource
    private SysUserService sysUserService;

    // @RequestMapping("role.page")
    // public ModelAndView page() {
    //     return new ModelAndView("role");
    // }

    @RequestMapping("/save")
    public ResultVO saveRole(@Valid @RequestBody RoleParam param) {
        sysRoleService.save(param);
        return ResultVOUtil.success();
    }

    @RequestMapping("/update")
    public ResultVO updateRole(RoleParam param) {
        sysRoleService.update(param);
        return ResultVOUtil.success();
    }

    @RequestMapping("/list")
    public ResultVO list() {
        return ResultVOUtil.success(sysRoleService.getAll());
    }

    @RequestMapping("/roleTree")
    public ResultVO roleTree(@RequestParam("roleId") int roleId) {
        return ResultVOUtil.success(sysTreeService.roleTree(roleId));
    }

    @RequestMapping("/changeAcls")
    public ResultVO changeAcls(@RequestParam("roleId") int roleId, @RequestParam(value = "aclIds", required = false, defaultValue = "") String aclIds) {
        List<Integer> aclIdList = StringUtil.splitToListInt(aclIds);
        sysRoleAclService.changeRoleAcls(roleId, aclIdList);
        return ResultVOUtil.success();
    }

    @RequestMapping("/changeUsers")
    @ResponseBody
    public ResultVO changeUsers(@RequestParam("roleId") int roleId, @RequestParam(value = "userIds", required = false, defaultValue = "") String userIds) {
        List<Integer> userIdList = StringUtil.splitToListInt(userIds);
        sysRoleUserService.changeRoleUsers(roleId, userIdList);
        return ResultVOUtil.success();
    }

    @RequestMapping("/users")
    @ResponseBody
    public ResultVO users(@RequestParam("roleId") int roleId) {
        List<SysUser> selectedUserList = sysRoleUserService.getListByRoleId(roleId);
        List<SysUser> allUserList = sysUserService.getAll();
        List<SysUser> unselectedUserList = Lists.newArrayList();

        Set<Integer> selectedUserIdSet = selectedUserList.stream().map(sysUser -> sysUser.getId()).collect(Collectors.toSet());
        for(SysUser sysUser : allUserList) {
            if (sysUser.getStatus() == 1 && !selectedUserIdSet.contains(sysUser.getId())) {
                unselectedUserList.add(sysUser);
            }
        }
        // selectedUserList = selectedUserList.stream().filter(sysUser -> sysUser.getStatus() != 1).collect(Collectors.toList());
        Map<String, List<SysUser>> map = Maps.newHashMap();
        map.put("selected", selectedUserList);
        map.put("unselected", unselectedUserList);
        return ResultVOUtil.success(map);
    }
}
