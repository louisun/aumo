package com.louisun.controller;

import com.alibaba.fastjson.JSONObject;
import com.louisun.model.Post;
import com.louisun.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    /**
     * 添加帖子 /post POST
     * @param post 请求体转换的 POST 对象，包含：版面id、用户id、标题、Markdown格式的内容
     * @return JSONObject
     */
    @PostMapping("/post")
    public JSONObject addPost(@RequestBody Post post){
        return postService.insertPost(post);
    }

    /**
     * 获取某版块下所有帖子（不含评论，按发布时间逆序）
     * @param tagId 版块id
     * @return JSONObject
     */
    @GetMapping("/tag/{tagId}/posts")
    public JSONObject getPostsByTag(@PathVariable("tagId") Integer tagId){
        return postService.getPostsByTag(tagId);
    }

    /**
     * 获取某用户所有帖子（不含评论，按更新时间逆序）
     * @param tagId 版块id
     * @return JSONObject
     */
    @GetMapping("/user/{userId}/posts")
    public JSONObject getPostsByUserId(@PathVariable("userId") Integer userId){
        return postService.getPostsByUserId(userId);
    }

    /**
     * 获取帖子详细内容（包含评论列表）
     * @param tagId 版块id
     * @return JSONObject
     */
    @GetMapping("/post/{postId}")
    public JSONObject getPostsByPostId(@PathVariable("postId") Integer postId){
        return postService.getPostById(postId);
    }
}
