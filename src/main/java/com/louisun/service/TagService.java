package com.louisun.service;

import com.alibaba.fastjson.JSONObject;
import com.louisun.model.Tag;

public interface TagService {
    /**
     * 获取所有版块
     * @param null
     * @return JSONObject
     * @author YeJianan
     * @date 2019/1/28 16:10
     */
    JSONObject getAllTags();

    /**
     * 根据tagId获取版块名
     * @param tagId 版块 id
     * @return JSONObject
     * @author YeJianan
     * @date 2019/1/28 16:10
     */
    JSONObject getTagById(int tagId);

    /**
     * 插入新的版块
     * @param tag 版块
     * @return JSONObject
     * @author YeJianan
     * @date 2019/1/28 20:18
     */
    JSONObject insertTag(Tag tag);
}
