package com.linqi.userservice.param;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class AclParam {

    private Integer id;

    @NotBlank(message = "权限点名称不可以为空")
    @Size(max = 20,min = 2,message = "权限点名称长度需要在2-20个字之间")
    private String name;

    @NotNull(message = "必须指定权限模块")
    private Integer aclModuleId;

    @Size(max = 100,min = 6,message = "权限点URL长度需要在6-100个字符之间")
    private String url;

    @NotNull(message = "必须指定权限点的类型")
    @Min(value = 1, message = "权限点类型不合法")
    @Max(value = 3, message = "权限点类型不合法")
    private Integer type;

    @NotNull(message = "必须指定权限点的状态")
    @Min(value = 0, message = "权限点状态不合法")
    @Max(value = 1, message = "权限点状态不合法")
    private Integer status;

    @NotNull(message = "必须指定权限点的展示顺序")
    private Integer seq;

    @Size(max = 200,message = "权限点备注长度需要在200个字符以内")
    private String remark;
}
