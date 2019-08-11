package com.linqi.userservice.param;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class RoleParam {

    private Integer id;

    @NotBlank(message = "角色名称不可以为空")
    @Size(max = 20,min=2,message = "角色名称长度需要在2-20个字之间")
    private String name;

    @Min(value = 1, message = "角色类型不合法")
    @Max(value = 2, message = "角色类型不合法")
    private Integer type = 1;

    @NotNull(message = "角色状态不可以为空")
    @Min(value = 0, message = "角色状态不合法")
    @Max(value = 1, message = "角色状态不合法")
    private Integer status;

    @Size(max = 200,message = "角色备注长度需要在200个字符以内")
    private String remark;
}
