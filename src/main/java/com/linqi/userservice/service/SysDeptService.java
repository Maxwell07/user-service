package com.linqi.userservice.service;

import com.google.common.base.Preconditions;
import com.linqi.userservice.common.enums.DeptResultEnum;
import com.linqi.userservice.common.exception.ParamException;
import com.linqi.userservice.common.utils.IpUtil;
import com.linqi.userservice.common.utils.LevelUtil;
import com.linqi.userservice.common.utils.RequestHolder;
import com.linqi.userservice.dao.SysDeptMapper;
import com.linqi.userservice.dao.SysUserMapper;
import com.linqi.userservice.model.SysDept;
import com.linqi.userservice.param.DeptParam;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class SysDeptService {

    @Resource
    private SysDeptMapper sysDeptMapper;
    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private SysLogService sysLogService;

    public void save(DeptParam param) {
        if(checkExist(param.getParentId(), param.getName(), param.getId())) {
            throw new ParamException(DeptResultEnum.EXIST_SAME_DEPT);
        }
        SysDept dept = SysDept.builder().name(param.getName()).parentId(param.getParentId())
                .seq(param.getSeq()).remark(param.getRemark()).build();

        dept.setLevel(LevelUtil.calculateLevel(getLevel(param.getParentId()), param.getParentId()));
        // dept.setOperator(RequestHolder.getCurrentUser().getUsername());   // TODO: 2019/8/11 请求IP会报空指针异常
        // dept.setOperateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        dept.setOperateTime(new Date());
        sysDeptMapper.insertSelective(dept);
        sysLogService.saveDeptLog(null, dept);
    }

    public void update(DeptParam param) {
        if(checkExist(param.getParentId(), param.getName(), param.getId())) {
            throw new ParamException(DeptResultEnum.EXIST_SAME_DEPT);
        }
        SysDept before = sysDeptMapper.selectByPrimaryKey(param.getId());
        Preconditions.checkNotNull(before, "待更新的部门不存在");
        if(checkExist(param.getParentId(), param.getName(), param.getId())) {
            throw new ParamException(DeptResultEnum.EXIST_SAME_DEPT);
        }

        SysDept after = SysDept.builder().id(param.getId()).name(param.getName()).parentId(param.getParentId())
                .seq(param.getSeq()).remark(param.getRemark()).build();
        after.setLevel(LevelUtil.calculateLevel(getLevel(param.getParentId()), param.getParentId()));
        after.setOperator(RequestHolder.getCurrentUser().getUsername());
        after.setOperateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        after.setOperateTime(new Date());

        updateWithChild(before, after);
        sysLogService.saveDeptLog(before, after);
    }

    @Transactional
    private void updateWithChild(SysDept before, SysDept after) {
        String newLevelPrefix = after.getLevel();
        String oldLevelPrefix = before.getLevel();
        if (!after.getLevel().equals(before.getLevel())) {
            List<SysDept> deptList = sysDeptMapper.getChildDeptListByLevel(before.getLevel());
            if (CollectionUtils.isNotEmpty(deptList)) {
                for (SysDept dept : deptList) {
                    String level = dept.getLevel();
                    if (level.indexOf(oldLevelPrefix) == 0) {
                        level = newLevelPrefix + level.substring(oldLevelPrefix.length());
                        dept.setLevel(level);
                    }
                }
                sysDeptMapper.batchUpdateLevel(deptList);
            }
        }
        sysDeptMapper.updateByPrimaryKey(after);
    }

    private boolean checkExist(Integer parentId, String deptName, Integer deptId) {
        return sysDeptMapper.countByNameAndParentId(parentId, deptName, deptId) > 0;
    }

    private String getLevel(Integer deptId) {
        SysDept dept = sysDeptMapper.selectByPrimaryKey(deptId);
        if (dept == null) {
            return null;
        }
        return dept.getLevel();
    }

    public void delete(int deptId) {
        SysDept dept = sysDeptMapper.selectByPrimaryKey(deptId);
        Preconditions.checkNotNull(dept, "待删除的部门不存在，无法删除");
        if (sysDeptMapper.countByParentId(dept.getId()) > 0) {
            throw new ParamException(DeptResultEnum.HAS_CHILD_DEPT_CANT_DELETE);
        }
        if(sysUserMapper.countByDeptId(dept.getId()) > 0) {
            throw new ParamException(DeptResultEnum.HAS_CHILD_DEPT_CANT_DELETE);
        }
        sysDeptMapper.deleteByPrimaryKey(deptId);
    }
}
