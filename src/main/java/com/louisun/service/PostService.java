package com.louisun.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.louisun.model.Post;
import com.louisun.util.PageBean;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface PostService {
    /**
     * 保存帖子信息
     * @param post 帖子：包含 postId, tagId, userId, title, content, contentMd
     * @return int 插入成功的条数
     * @author YeJianan
     * @date 2019/1/27 23:40
     */
    int insertPost(Post post);

    /**
     * 根据 tagId 获取帖子基本信息列表
     * @param tagId 版块 ID
     * @return List<Post> 帖子基本信息列表
     * @author YeJianan
     * @date 2019/1/27 23:40
     */
    PageBean<Post> getPostsByTag(int tagId, int currentPage, int pageSize);

    /**
     * 根据 userId 获取某用户所有帖子基本信息列表
     * @param userId 用户 ID
     * @return List<Post> 帖子基本信息列表
     * @author YeJianan
     * @date 2019/1/27 23:41
     */
    PageBean<Post> getPostsByUserId(int userId, int currentPage, int pageSize);

    /**
     * 根据 postId 获取帖子详细内容（包含评论列表）
     * @param postId 帖子 ID
     * @return JSONObject
     * @author YeJianan
     * @date 2019/1/27 23:42
     */
    Post getPostById(int postId);
}
