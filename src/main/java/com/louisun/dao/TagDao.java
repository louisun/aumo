package com.louisun.dao;

import com.louisun.model.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TagDao {
    int deleteByPrimaryKey(Integer tagId);

    int insert(String tag_name);

    int insertSelective(Tag record);

    Tag selectByPrimaryKey(Integer tagId);

    int updateByPrimaryKeySelective(Tag record);

    List<Tag> selectAlltag();

    int updateByPrimaryKey(Tag record);
}