package com.louisun.service;

import com.alibaba.fastjson.JSONObject;
import com.louisun.model.Comment;

public interface CommentService {

    /**
     * 添加关于某篇文章的一条评论
     * @param comment 评论
     * @return JSONObject
     * @author YeJianan
     * @date 2019/1/28 16:00
     */
    JSONObject insertComment(Comment comment);

    /**
     * 根据帖子的id获取所有评论
     * @param postId 帖子 id
     * @return JSONObject
     * @author YeJianan
     * @date 2019/1/28 16:01
     */
    JSONObject getCommentByPostId(int postId);

    /**
     * 根据用户id获取该用户提交的所有评论（按时间逆序）
     * @param userId 用户 id
     * @return JSONObject
     * @author YeJianan
     * @date 2019/1/28 16:02
     */
    JSONObject getCommentByUserId(int userId);

    /**
     * 根据评论id删除评论
     * @param commentId 评论 id
     * @return JSONObject
     * @author YeJianan
     * @date 2019/1/28 16:07
     */
    JSONObject deleteCommentByCommentId(int commentId);

}
