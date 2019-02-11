package com.louisun.controller;

import com.alibaba.fastjson.JSONObject;
import com.louisun.model.Comment;
import com.louisun.service.CommentService;
import com.louisun.util.JsonResult;
import com.louisun.util.constant.ErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    /**
     * 在帖子下添加评论 /post/{postId}/comment POST
     * @param comment 请求体转换的 Comment 对象：包含帖子id，用户id，回复id，评论内容
     * @return  JSONObject
     */
    @PostMapping("/post/{postId}/comment")
    public JSONObject addComment(@RequestBody Comment comment, @PathVariable("postId") Integer postId){
        comment.setPostId(postId);
        return commentService.insertComment(comment);
    }

    /**
     * 获取帖子下的所有评论 /post/{postId}/comments/ GET
     * @param postId 帖子id
     * @return  JSONObject
     */
    @GetMapping("/post/{postId}/comments")
    public JSONObject getCommentsByPostId(@PathVariable("postId") Integer postId){
        return commentService.getCommentByPostId(postId);
    }

    /**
     * 获取某用户的所有评论 /post/{postId}/comments/ GET
     * @param userId 用户id
     * @return  JSONObject
     */
    @GetMapping("/user/{userId}/comments")
    public  JSONObject getCommentsByUserId(@PathVariable("userId") Integer userId){
        return commentService.getCommentByUserId(userId);
    }

    /**
     * 删除当前用户的某条评论 /post/{postId}/comments/ DELETE
     * @param currentUserId 当前 session 中保存的用户id
     * @param userId 用户id
     * @param commentId 帖子id
     * @return  JSONObject
     */
    @DeleteMapping("/user/{userId}/comment/{commentId}")
    public JSONObject deleteComment(@SessionAttribute("userId") Integer currentUserId, @PathVariable("userId") Integer userId, @PathVariable("commentId") Integer commentId){
        if(currentUserId.equals(userId)){
            return commentService.deleteCommentByCommentId(commentId);
        }
        else{
            return JsonResult.errorResult(ErrorEnum.E_6003); // 不能删除其他用户的评论
        }
    }
}
