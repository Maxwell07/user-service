package com.linqi.userservice.controller;

import com.linqi.userservice.common.response.ResultVO;
import com.linqi.userservice.common.response.ResultVOUtil;
import com.linqi.userservice.dto.DeptLevelDto;
import com.linqi.userservice.param.DeptParam;
import com.linqi.userservice.service.SysDeptService;
import com.linqi.userservice.service.SysTreeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/sys/dept")
@Slf4j
public class SysDeptController {

    @Resource
    private SysDeptService sysDeptService;
    @Resource
    private SysTreeService sysTreeService;

    // @RequestMapping("/dept.page")
    // public ModelAndView page() {
    //     return new ModelAndView("dept");
    // }

    @PostMapping("/save")
    public ResultVO saveDept(@Valid @RequestBody DeptParam param, HttpServletRequest request) {
        sysDeptService.save(param);
        return ResultVOUtil.success();
    }

    @PostMapping("/tree")
    public ResultVO tree() {
        List<DeptLevelDto> dtoList = sysTreeService.deptTree();
        return ResultVOUtil.success(dtoList);
    }

    @PostMapping("/update")
    public ResultVO updateDept(@Valid @RequestBody DeptParam param) {
        sysDeptService.update(param);
        return ResultVOUtil.success();
    }

    @PostMapping("/delete")
    public ResultVO delete(@RequestParam("id") int id) {
        sysDeptService.delete(id);
        return ResultVOUtil.success();
    }

}
