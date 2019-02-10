package com.louisun.dao;

import com.louisun.model.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TagDao {
    int deleteByTagId(Integer tagId);

    int insertTag(Tag record);

    Tag selectByTagId(Integer tagId);

    List<Tag> selectAllTags();

    int updateByTagId(Tag record);
}