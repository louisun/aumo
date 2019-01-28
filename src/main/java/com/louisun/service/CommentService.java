package com.louisun.service;

import com.alibaba.fastjson.JSONObject;
import com.louisun.model.Comment;

public interface CommentService {

    /*
     * 插入对于某篇文章的一条评论
     * @param jsonObject
     * @return com.alibaba.fastjson.JSONObject
     * @author YeJianan
     * @date 2019/1/28 16:00
     */
    public JSONObject insertComment(Comment comment);

    /*
     * 根据文章的id来获取所有的评论
     * @param comment_id
     * @return com.alibaba.fastjson.JSONObject
     * @author YeJianan
     * @date 2019/1/28 16:01
     */
    public JSONObject getCommentByPostId(int post_id);

    /*
     * 根据UserId获取该用户提交的所有评论(按照时间排序)
     * @param user_id
     * @return com.alibaba.fastjson.JSONObject
     * @author YeJianan
     * @date 2019/1/28 16:02
     */
    public JSONObject getCommentByUserId(int user_id);

    /*
     * 根据评论的ID，删除某条评论
     * @param comment_id
     * @return com.alibaba.fastjson.JSONObject
     * @author YeJianan
     * @date 2019/1/28 16:07
     */
    public JSONObject deleteCommentByCommentId(int comment_id);

}
