package com.louisun.dao;

import org.apache.ibatis.annotations.Mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PermissionDao {
    List<String> getUserPermissions(@Param("userId")int userId);

}
