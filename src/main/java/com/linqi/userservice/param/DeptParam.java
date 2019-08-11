package com.linqi.userservice.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class DeptParam {

    private Integer id;

    @NotBlank(message = "部门名称不可以为空")
    @Size(max = 15,min = 2,message = "部门名称长度需要在2-15个字之间")
    private String name;

    private Integer parentId = 0;

    @NotNull(message = "展示顺序不可以为空")
    private Integer seq;

    @Size(max = 150, message = "备注的长度需要在150个字以内")
    private String remark;
}
