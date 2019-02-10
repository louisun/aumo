package com.louisun.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.louisun.dao.CommentDao;
import com.louisun.model.Comment;
import com.louisun.service.CommentService;
import com.louisun.util.JsonResult;
import com.louisun.util.constant.ErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
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
     * 添加关于某篇文章的一条评论
     * @param comment 评论
     * @return JSONObject
     * @author YeJianan
     * @date 2019/1/28 16:00
     */
    @Override
    public JSONObject insertComment(Comment comment){
        if(commentDao.insertComment(comment)==0){
            return  JsonResult.errorResult(ErrorEnum.E_6001);
        }
        else{
            return JsonResult.successResult(comment);
        }
    }

    /**
     * 根据帖子的id获取所有评论
     * @param postId 帖子 id
     * @return JSONObject
     * @author YeJianan
     * @date 2019/1/28 16:01
     */
    @Override
    public JSONObject getCommentByPostId(int postId){
        List<Comment> commentList= commentDao.selectByPostId(postId);
        if(commentList==null){
            return JsonResult.errorResult(ErrorEnum.E_6002);
        }
        else{
            return JsonResult.successResult(commentList);
        }
    }

    /**
     * 根据用户id获取该用户提交的所有评论（按时间逆序）
     * @param userId 用户 id
     * @return JSONObject
     * @author YeJianan
     * @date 2019/1/28 16:02
     */
    @Override
    public JSONObject getCommentByUserId(int userId){
        List<Comment> commentList= commentDao.selectByUserId(userId);
        if(commentList==null){
            return JsonResult.errorResult(ErrorEnum.E_6002);
        }
        else{
            return JsonResult.successResult(commentList);
        }
    }

    /**
     * 根据评论id删除评论
     * @param commentId 评论 id
     * @return JSONObject
     * @author YeJianan
     * @date 2019/1/28 16:07
     */
    @Override
    public JSONObject deleteCommentByCommentId(int commentId){
        if(commentDao.deleteByCommentId(commentId)==0){
            return JsonResult.errorResult(ErrorEnum.E_6003);
        }
        else{
            return JsonResult.successResult();
        }
    }
}
