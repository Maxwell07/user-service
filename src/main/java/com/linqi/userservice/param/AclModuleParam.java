package com.linqi.userservice.param;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class AclModuleParam {

    private Integer id;

    @NotBlank(message = "权限模块名称不可以为空")
    @Size(max = 20,min=2,message = "权限模块名称长度应为2~20个字符")
    private String name;

    private Integer parentId = 0;

    @NotNull(message = "权限模块展示顺序不可以为空")
    private Integer seq;

    @NotNull(message = "权限模块状态不可以为空")
    @Min(value = 0, message = "权限模块状态不合法")
    @Max(value = 1, message = "权限模块状态不合法")
    private Integer status;

    @Size(max = 200,message = "权限模块备注需要在200个字之间")
    private String remark;
}
