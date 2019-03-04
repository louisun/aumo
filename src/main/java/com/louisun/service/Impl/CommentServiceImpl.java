package com.louisun.service.Impl;

import com.louisun.dao.CommentDao;
import com.louisun.model.Comment;
import com.louisun.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentDao commentDao;

    @Autowired
    public CommentServiceImpl(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    /**
     * 根据评论id获取评论
     *
     * @param commentId 评论 id
     * @return JSONObject
     * @author YeJianan
     * @date 2019/1/28 16:01
     */
    @Override
    public Comment getCommentById(int commentId) {
        return commentDao.selectByCommentId(commentId);
    }

    /**
     * 添加关于某篇文章的一条评论
     * @param comment 评论
     * @return JSONObject
     * @author YeJianan
     * @date 2019/1/28 16:00
     */
    @Override
    public int insertComment(Comment comment){
        return commentDao.insertComment(comment);
    }

    /**
     * 根据帖子的id获取所有评论
     * @param postId 帖子 id
     * @return JSONObject
     * @author YeJianan
     * @date 2019/1/28 16:01
     */
    @Override
    public List<Comment> getCommentsByPostId(int postId){
        return commentDao.selectByPostId(postId);

    }

    /**
     * 根据用户id获取该用户提交的所有评论（按时间逆序）
     * @param userId 用户 id
     * @return JSONObject
     * @author YeJianan
     * @date 2019/1/28 16:02
     */
    @Override
    public List<Comment> getCommentByUserId(int userId){
        return commentDao.selectByUserId(userId);
    }

    /**
     * 根据评论id删除评论
     * @param commentId 评论 id
     * @return JSONObject
     * @author YeJianan
     * @date 2019/1/28 16:07
     */
    @Override
    public int deleteCommentByCommentId(int commentId){
        return commentDao.deleteByCommentId(commentId);

    }
}
