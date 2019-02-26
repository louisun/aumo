package com.louisun.model;

import com.sun.xml.internal.bind.v2.model.core.ID;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class Role implements Serializable {
    private static final long serialVersionUID = 5175467892960950267L;

    private Integer roleId;

    private String roleName;

    private Date createTime;

    private Date updateTime;
}