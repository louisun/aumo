package com.louisun.controller;

import com.alibaba.fastjson.JSONObject;
import com.louisun.model.Tag;
import com.louisun.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TagController {

    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    /**
     * 获取所有标签
     *
     * @param null
     * @return JSONObject
     */
    @GetMapping("/tags")
    public JSONObject getTags() {
        return tagService.getAllTags();

    }

    /**
     * 添加版块 /tag POST
     * @param tag 请求体转换的 Tag 对象，包含：版块名
     * @return JSONObject
     */
    @PostMapping("/tag")
    public JSONObject addTag(@RequestBody Tag tag) {
        return tagService.insertTag(tag);
    }
}
