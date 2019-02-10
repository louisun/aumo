package com.louisun.service;

import com.alibaba.fastjson.JSONObject;
import com.louisun.model.Post;

public interface PostService {
    /**
     * 保存帖子信息
     * @param post 帖子：包含 postId, tagId, userId, title, content, contentMd
     * @return JSONObject
     * @author YeJianan
     * @date 2019/1/27 23:40
     */
    JSONObject insertPost(Post post);

    /**
     * 根据 tagId 获取帖子基本信息列表
     * @param tagId 版块 ID
     * @return JSONObject
     * @author YeJianan
     * @date 2019/1/27 23:40
     */
    JSONObject getPostsByTag(int tagId);

    /**
     * 根据 userId 获取某用户所有帖子基本信息列表
     * @param userId 用户 ID
     * @return JSONObject
     * @author YeJianan
     * @date 2019/1/27 23:41
     */
    JSONObject getPostsByUserId(int userId);

    /**
     * 根据 postId 获取帖子详细内容（包含评论列表）
     * @param postId 帖子 ID
     * @return JSONObject
     * @author YeJianan
     * @date 2019/1/27 23:42
     */
    JSONObject getPostById(int postId);

    /**
     * 根据 postId 删除帖子
     * @param postId 帖子 ID
     * @return JSONObject
     * @author YeJianan
     * @date 2019/1/27 23:43
     */
    JSONObject deletePostById(int postId);

    /**
     * 根据 userId 删除某用户所有帖子
     * @param userId 用户 ID
     * @return JSONObject
     * @author YeJianan
     * @date 2019/1/27 23:43
     */
    JSONObject deletePostsByUserId(int userId);
}
