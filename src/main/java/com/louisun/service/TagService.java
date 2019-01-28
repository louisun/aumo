package com.louisun.service;

import com.alibaba.fastjson.JSONObject;

public interface TagService {

    /*
     * 获取目前的网站中存在的所有tag
     * @param
     * @return com.alibaba.fastjson.JSONObject
     * @author YeJianan
     * @date 2019/1/28 16:10
     */
    public JSONObject getAllTags();

    /*
     * 根据tag_id来获取这个tag的名字
     * @param tag_id
     * @return java.lang.String
     * @author YeJianan
     * @date 2019/1/28 16:10
     */
    public String getTagById(int tag_id);

    /*
     * 表示插入一个新的tag
     * @param name
     * @return com.alibaba.fastjson.JSONObject
     * @author YeJianan
     * @date 2019/1/28 20:18
     */
    public JSONObject insertTag(String name);


}
