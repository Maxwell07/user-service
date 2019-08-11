// package com.linqi.userservice.dept.service.impl;
//
// import com.google.common.base.Preconditions;
// import com.linqi.userservice.common.exception.ParamException;
// import com.linqi.userservice.dao.SysDeptMapper;
// import com.linqi.userservice.dept.enums.DeptResultEnum;
// import com.linqi.userservice.dept.param.DeptParam;
// import com.linqi.userservice.dept.service.SysDeptService;
// import com.linqi.userservice.dept.util.LevelUtil;
// import com.linqi.userservice.model.SysDept;
// import org.apache.commons.collections.CollectionUtils;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;
//
// import javax.annotation.Resource;
// import java.util.Date;
// import java.util.List;
//
// @Service
// public class SysDeptServiceImpl implements SysDeptService {
//
//     @Resource
//     private SysDeptMapper sysDeptMapper;
//
//     @Override
//     public void save(DeptParam param) {
//         if (checkExist(param.getParentId(), param.getName(), param.getId())) {
//             throw new ParamException(DeptResultEnum.EXIST_SAME_DEPT);
//         }
//         SysDept dept = SysDept.builder().name(param.getName()).parentId(param.getParentId())
//                 .seq(param.getSeq()).remark(param.getRemark()).build();
//         dept.setLevel(LevelUtil.calculateLevel(getLevel(param.getId()), param.getParentId()));
//         dept.setOperator("system"); // TODO: 2019/8/10
//         dept.setOperateIp("127.0.0.1"); // TODO
//         dept.setOperateTime(new Date());
//         sysDeptMapper.insert(dept);
//     }
//
//     @Override
//     @Transactional
//     public void update(DeptParam param) {
//         if (checkExist(param.getParentId(), param.getName(), param.getId())) {
//             throw new ParamException(DeptResultEnum.EXIST_SAME_DEPT);
//         }
//         SysDept before = sysDeptMapper.selectByPrimaryKey(param.getId());
//         Preconditions.checkNotNull(before, "待更新的部门不存在");
//
//         if (checkExist(param.getParentId(), param.getName(), param.getId())) {
//             throw new ParamException(DeptResultEnum.EXIST_SAME_DEPT);
//         }
//
//         SysDept after = SysDept.builder().id(param.getId()).name(param.getName()).parentId(param.getParentId())
//                 .seq(param.getSeq()).remark(param.getRemark()).build();
//         after.setLevel(LevelUtil.calculateLevel(getLevel(param.getParentId()), param.getParentId()));
//         after.setOperator("system"); // TODO: 2019/8/10
//         after.setOperateIp("127.0.0.1"); // TODO
//         after.setOperateTime(new Date());
//     }
//
//
//     private void updateWithChild(SysDept before, SysDept after) {
//         sysDeptMapper.updateByPrimaryKey(after);
//         String newLevelPrefix = after.getLevel();
//         String oldLevelPrefix = before.getLevel();
//         if (!after.getLevel().equals(before.getLevel())) {
//             List<SysDept> deptList = sysDeptMapper.getChildDeptListByLevel(before.getLevel());
//             if (CollectionUtils.isNotEmpty(deptList)) {
//                 for (SysDept sysDept : deptList) {
//                     String level = sysDept.getLevel();
//                     if (level.indexOf(oldLevelPrefix) == 0) {
//                         level = newLevelPrefix + level.substring(oldLevelPrefix.length());
//                         sysDept.setLevel(level);
//                     }
//                 }
//                 sysDeptMapper.batchUpdateLevel(deptList);
//             }
//         }
//
//         sysDeptMapper.updateByPrimaryKey(after);
//     }
//
//     private boolean checkExist(Integer parentId, String deptName, Integer deptId) {
//         // TODO :
//         return true;
//     }
//
//     private String getLevel(Integer deptId) {
//         SysDept dept = sysDeptMapper.selectByPrimaryKey(deptId);
//         if (dept == null) {
//             return null;
//         }
//         return dept.getLevel();
//     }
//
// }
