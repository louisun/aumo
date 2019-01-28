package com.louisun.service;

import com.alibaba.fastjson.JSONObject;

public interface PostService {
    /*
     * 插入一条新的post记录
     * @param jsonObject
     * @return com.alibaba.fastjson.JSONObject
     * @author YeJianan
     * @date 2019/1/27 23:40
     */
    public JSONObject insertPostContent(JSONObject jsonObject);

    /*
     * 通过tagId来获取帖子内容的列表
     * @param tag_id
     * @return com.alibaba.fastjson.JSONObject
     * @author YeJianan
     * @date 2019/1/27 23:40
     */
    public JSONObject getContentByTagId(int tag_id);

    /*
     * 根据UserId来获取某个用户的所有帖子的列表（按照时间排序）
     * @param user_id
     * @return com.alibaba.fastjson.JSONObject
     * @author YeJianan
     * @date 2019/1/27 23:41
     */
    public JSONObject getContentByUserId(int user_id);

    /*
     * 根据PostId来详细地获取文章的所有内容
     * @param post_id
     * @return com.alibaba.fastjson.JSONObject
     * @author YeJianan
     * @date 2019/1/27 23:42
     */
    public JSONObject getContentByPostId(int post_id);

    /*
     * 根据psotId来删除谋篇文章
     * @param post_id
     * @return com.alibaba.fastjson.JSONObject
     * @author YeJianan
     * @date 2019/1/27 23:43
     */
    public JSONObject deleteContentByPostId(int post_id);

    /*
     * 根据UserId来删除某用户下的所有文章
     * @param user_id
     * @return com.alibaba.fastjson.JSONObject
     * @author YeJianan
     * @date 2019/1/27 23:43
     */
    public JSONObject deleteContentByUserId(int user_id);
}
