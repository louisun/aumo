package com.louisun.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Tag implements Serializable {
    private  static final long serialVersionUID = -1247825749004468064L;
    private Integer tagId;
    private String name;
}