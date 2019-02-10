package com.louisun.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Role {
    private Integer roleId;

    private String roleName;

    private Date createTime;

    private Date updateTime;
}