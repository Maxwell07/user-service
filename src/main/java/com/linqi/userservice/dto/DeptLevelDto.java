package com.linqi.userservice.dto;

import com.google.common.collect.Lists;
import com.linqi.userservice.model.SysDept;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Data
public class DeptLevelDto extends SysDept {

    private List<DeptLevelDto> deptList = Lists.newArrayList();

    public static DeptLevelDto adapt(SysDept dept) {
        DeptLevelDto dto = new DeptLevelDto();
        BeanUtils.copyProperties(dept, dto);
        return dto;
    }
}
