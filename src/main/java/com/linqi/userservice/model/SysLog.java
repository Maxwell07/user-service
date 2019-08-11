package com.linqi.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SysLog {
    private Integer id;

    private Integer type;

    private Integer targetId;

    private String operator;

    private Date operateTime;

    private String operateIp;

    private Integer status;

}