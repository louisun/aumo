package com.louisun.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.louisun.dao.CommentDao;
import com.louisun.model.Comment;
import com.louisun.service.CommentService;
import com.louisun.util.JsonResult;
import com.louisun.util.constant.ErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentdao=null;

    @Override
    public JSONObject insertComment(Comment comment){
        if(commentdao.insertComment(comment)==0){
            return  JsonResult.errorResult(ErrorEnum.E_5001);
        }
        else{
            return JsonResult.successResult(comment);
        }
    }

    @Override
    public JSONObject getCommentByPostId(int post_id){
        List<Comment> commentList=commentdao.selectByPostId(post_id);
        if(commentList==null){
            return JsonResult.errorResult(ErrorEnum.E_5002);
        }
        else{
            return JsonResult.successResult(commentList);
        }
    }

    @Override
    public JSONObject getCommentByUserId(int user_id){
        List<Comment> commentList=commentdao.selectByUserId(user_id);
        if(commentList==null){
            return JsonResult.errorResult(ErrorEnum.E_5003);
        }
        else{
            return JsonResult.successResult(commentList);
        }
    }

    @Override
    public JSONObject deleteCommentByCommentId(int comment_id){
        if(commentdao.deleteByCommentId(comment_id)==0){
            return JsonResult.errorResult(ErrorEnum.E_5004);
        }
        else{
            return JsonResult.successResult();
        }
    }
}
