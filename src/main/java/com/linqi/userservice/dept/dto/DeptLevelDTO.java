// package com.linqi.userservice.dept.dto;
//
// import com.google.common.collect.Lists;
// import com.linqi.userservice.model.SysDept;
// import lombok.Data;
// import org.springframework.beans.BeanUtils;
//
// import java.util.List;
//
//
// @Data
// public class DeptLevelDTO extends SysDept {
//     private List<DeptLevelDTO> deptList = Lists.newArrayList();
//
//     public static DeptLevelDTO adapt(SysDept dept) {
//         DeptLevelDTO deptLevelDTO = new DeptLevelDTO();
//         BeanUtils.copyProperties(dept, deptLevelDTO);
//         return deptLevelDTO;
//     }
// }
