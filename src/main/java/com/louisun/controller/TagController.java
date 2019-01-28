package com.louisun.controller;

import com.alibaba.fastjson.JSONObject;
import com.louisun.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TagController {

    @Autowired
    private TagService tagService=null;

    /** 获得所有的tag名 **/
    @GetMapping("/getTags")
    public JSONObject getAllTags(){
        return tagService.getAllTags();
    }

    /** 插入一个tag **/
    @PostMapping("/insertTag")
    public JSONObject insertTags(@RequestBody JSONObject requestBody){
        String tagName=requestBody.getString("tagname");
        return tagService.insertTag(tagName);
    }
}
