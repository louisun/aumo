package com.louisun.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.louisun.dao.TagDao;
import com.louisun.model.Tag;
import com.louisun.service.TagService;
import com.louisun.util.JsonResult;
import com.louisun.util.constant.ErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    private final TagDao tagDao;

    @Autowired
    public TagServiceImpl(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    /**
     * 获取所有版块
     * @return JSONObject
     * @author YeJianan
     * @date 2019/1/28 16:10
     */
    @Override
    public JSONObject getAllTags() {
        List<Tag> tags = tagDao.selectAllTags();
        if (tags != null){
            return JsonResult.successResult(tags);
        }
        else{
            return JsonResult.errorResult(ErrorEnum.E_5002);
        }
    }


    /**
     * 插入新版块
     * @param tagId 版块id
     * @return JSONObject
     * @author YeJianan
     * @date 2019/1/28 20:18
     */
    @Override
    public JSONObject getTagById(int tagId) {
        Tag tag = tagDao.selectByTagId(tagId);
        if (tag == null) {
            return JsonResult.errorResult(ErrorEnum.E_5002);
        } else {
            return JsonResult.successResult(tag);
        }
    }

    /**
     * 表示插入一个新的tag
     * @param tag 版块
     * @return JSONObject
     * @author YeJianan
     * @date 2019/1/28 20:18
     */
    @Override
    public JSONObject insertTag(Tag tag) {
        if (tag.getName() == null) {
            return JsonResult.errorResult(ErrorEnum.E_5001);
        }
        if(tagDao.insertTag(tag) == 0){
            return JsonResult.errorResult(ErrorEnum.E_5001);
        }
        else{
            return JsonResult.successResult("添加版块成功");
        }
    }

}
