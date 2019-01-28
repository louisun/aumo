package com.louisun.controller;

import com.alibaba.fastjson.JSONObject;
import com.louisun.model.Comment;
import com.louisun.service.CommentService;
import com.louisun.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService=null;

    /** 对具体的某篇文章添加评论 **/
    @PostMapping("/addComment")
    public JSONObject addComment(@RequestBody JSONObject requestBody){
        Comment comment=new Comment();
        int user_id=Integer.valueOf(requestBody.get("user_id").toString());
        int post_id=Integer.valueOf(requestBody.get("post_id").toString());
        String content=requestBody.get("content").toString();
        int to_comment_id=0;
        if(requestBody.containsKey("to_comment_id")) {
            to_comment_id = Integer.valueOf(requestBody.get("to_comment_id").toString());
            comment.setToCommentId(to_comment_id);
        }
        comment.setUserId(user_id);
        comment.setPostId(post_id);
        comment.setContent(content);
        return commentService.insertComment(comment);
    }

    /** 查询某篇文章的全部评论 **/
    @GetMapping("/getComment")
    public JSONObject getComment(@RequestBody JSONObject requestBody){
        int post_id=Integer.valueOf(requestBody.get("postId").toString());
        System.out.println(post_id);
        return commentService.getCommentByPostId(post_id);
    }

    /** 查询某个用户的所有评论 **/
    @GetMapping("/getUserComment")
    public  JSONObject getUserComment(@RequestBody JSONObject requestBody){
        int user_id=Integer.valueOf(requestBody.get("userId").toString());
        return commentService.getCommentByUserId(user_id);
    }

    /** 删除某个用户的评论 **/
    @GetMapping("/deleteComment")
    public JSONObject deleteComment(@RequestBody JSONObject requestBody){
        int comment_id=Integer.valueOf(requestBody.get("commentId").toString());
        return commentService.deleteCommentByCommentId(comment_id);
    }
}
