package com.louisun.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.louisun.dao.TagDao;
import com.louisun.model.Tag;
import com.louisun.service.TagService;
import com.louisun.util.JsonResult;
import com.louisun.util.constant.ErrorEnum;
import com.louisun.util.constant.SuccessEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagDao tagDao=null;
    /*
     * 返回目前数据库中的所有的tag的名称，用于前端的展示
     * @param
     * @return com.alibaba.fastjson.JSONObject
     * @date 2019/1/28 16:51
     */
    @Override
    public JSONObject getAllTags(){
        JSONObject jsonResult = new JSONObject();
        List<Tag> tags=tagDao.selectAlltag();
        List<String> tags_name=new ArrayList<String>();

        if(tags!=null) {
            for (Tag a : tags) {
                tags_name.add(a.getName());
            }
            jsonResult.put("tagsName", tags_name);
            return JsonResult.successResult(jsonResult);
        }
        else
            return JsonResult.errorResult(ErrorEnum.E_4001);
    }

    @Override
    public String getTagById(int tag_id){
        Tag res=tagDao.selectByPrimaryKey(tag_id);
        return res.getName();
    }

    public JSONObject insertTag(String name){
        if(tagDao.insert(name)==0){
            return JsonResult.errorResult(ErrorEnum.E_4002);
        }
        else {
            return JsonResult.successResult(SuccessEnum.S_200);
        }
    }

}
