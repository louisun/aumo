package com.louisun.controller;

import com.alibaba.fastjson.JSONObject;
import com.louisun.model.Comment;
import com.louisun.model.Post;
import com.louisun.service.CommentService;
import com.louisun.service.RankService;
import com.louisun.util.JsonResult;
import com.louisun.util.constant.ErrorEnum;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    private final CommentService commentService;

    private final RankService rankService;

    @Autowired
    public CommentController(CommentService commentService, RankService rankService) {
        this.commentService = commentService;
        this.rankService = rankService;
    }

    /**
     * 在帖子下添加评论 /post/{postId}/comment POST
     * @param comment 请求体转换的 Comment 对象：包含帖子id，用户id，回复id，评论内容
     * @return  JSONObject
     */
    @PostMapping("/post/{postId}/comment")
    public JSONObject addComment(@RequestBody Comment comment, @PathVariable("postId") Integer postId){
        comment.setPostId(postId);
        int n = commentService.insertComment(comment);
        if(n==0){
            return  JsonResult.errorResult(ErrorEnum.E_6001);
        }
        else{
            // 在缓存中对 rank:post 对应 userId 的帖子数+1
            rankService.addPostScore(postId, 1);
            return JsonResult.successResult(comment);
        }
    }

    /**
     * 获取帖子下的所有评论 /post/{postId}/comments/ GET
     * @param postId 帖子id
     * @return  JSONObject
     */
    @GetMapping("/post/{postId}/comments")
    public JSONObject getCommentsByPostId(@PathVariable("postId") Integer postId){
        List<Comment> commentList = commentService.getCommentsByPostId(postId);
        return JsonResult.successResult(commentList);
    }

    /**
     * 获取某用户的所有评论 /post/{postId}/comments/ GET
     * @param userId 用户id
     * @return  JSONObject
     */
    @GetMapping("/user/{userId}/comments")
    public  JSONObject getCommentsByUserId(@PathVariable("userId") Integer userId){
        List<Comment> commentList = commentService.getCommentByUserId(userId);
        return JsonResult.successResult(commentList);
    }



    @RequiresPermissions("comment:delete")
    @DeleteMapping("/comment/{commentId}")
    public JSONObject deleteComment(@PathVariable("commentId") Integer commentId){
        Comment comment = commentService.getCommentById(commentId);
        int n = commentService.deleteCommentByCommentId(commentId);
        if(n==0){
            return JsonResult.errorResult(ErrorEnum.E_6003);
        }
        else{

            // 在缓存中对 rank:post 对应 userId 的帖子数-1
            rankService.addPostScore(comment.getPostId(), -1);
            return JsonResult.successResult("删除评论成功");
        }
    }
}
