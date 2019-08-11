// package com.linqi.userservice.dept.controller;
//
// import com.linqi.userservice.common.response.ResultVO;
// import com.linqi.userservice.common.response.ResultVOUtil;
// import com.linqi.userservice.dept.dto.DeptLevelDTO;
// import com.linqi.userservice.dept.param.DeptParam;
// import com.linqi.userservice.dept.service.SysDeptService;
// import com.linqi.userservice.dept.service.SysTreeService;
// import lombok.extern.slf4j.Slf4j;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
//
// import javax.validation.Valid;
// import java.util.List;
//
// @Slf4j
// @RestController
// @RequestMapping("/sys/dept")
// public class SysDeptController {
//
//     @Autowired
//     private SysDeptService sysDeptService;
//
//     @Autowired
//     private SysTreeService sysTreeService;
//
//     @PostMapping("/save")
//     public ResultVO saveDept(@Valid DeptParam deptParam) {
//         sysDeptService.save(deptParam);
//         return ResultVOUtil.success();
//     }
//
//     @PostMapping("/tree")
//     public ResultVO tree() {
//         List<DeptLevelDTO> dtoList = sysTreeService.deptTree();
//         return ResultVOUtil.success(dtoList);
//     }
//
//     @PostMapping("/update")
//     public ResultVO updateDept(@Valid DeptParam deptParam) {
//         sysDeptService.update(deptParam);
//         return ResultVOUtil.success();
//     }
//
// }
