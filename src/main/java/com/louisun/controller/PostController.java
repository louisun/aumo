package com.louisun.controller;

import com.alibaba.fastjson.JSONObject;
import com.louisun.model.Post;
import com.louisun.service.PostService;
import com.louisun.service.RankService;
import com.louisun.util.JsonResult;
import com.louisun.util.PageBean;
import com.louisun.util.constant.ErrorEnum;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostController {
    private final PostService postService;

    private final RankService rankService;

    @Autowired
    public PostController(PostService postService, RankService rankService) {
        this.postService = postService;
        this.rankService = rankService;
    }

    /**
     * 添加帖子 /post POST
     *
     * @param post 请求体转换的 POST 对象，包含：版面id、用户id、标题、Markdown格式的内容
     * @return JSONObject
     */
    @PostMapping("/post")
    public JSONObject addPost(@SessionAttribute("userInfo") JSONObject userInfo, @RequestBody Post post) {
        post.setUserId(userInfo.getIntValue("userId"));
        int result = postService.insertPost(post);
        if (result > 0) {
            // 在缓存中对 rank:user 对应 userId 的帖子数+1
            rankService.addUserScore(post.getUserId(), 1);
            return JsonResult.successResult("保存帖子成功");
        } else {
            return JsonResult.errorResult(ErrorEnum.E_4001);
        }
    }

    /**
     * 获取某版块下所有帖子（不含评论，按发布时间逆序）
     *
     * @param tagId 版块id
     * @return JSONObject
     */
    @GetMapping("/tag/{tagId}/posts")
    public JSONObject getPostsByTag(@PathVariable("tagId") Integer tagId, @RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage, @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        PageBean<Post> postList = postService.getPostsByTag(tagId, currentPage, pageSize);
        if (postList.getItems() == null || postList.getItems().size() == 0) {
            return JsonResult.errorResult(ErrorEnum.E_4002);
        } else {
            return JsonResult.successResult(postList);
        }
    }

    /**
     * 获取某用户所有帖子（不含评论，按更新时间逆序）
     *
     * @param userId 用户id
     * @return JSONObject
     */
    @GetMapping("/user/{userId}/posts")
    public JSONObject getPostsByUserId(@PathVariable("userId") Integer userId, @RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage, @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        PageBean<Post> postList = postService.getPostsByUserId(userId, currentPage, pageSize);
        if (postList.getItems() == null || postList.getItems().size() == 0) {
            return JsonResult.errorResult(ErrorEnum.E_4002);
        } else {
            return JsonResult.successResult(postList);
        }
    }

    /**
     * 获取帖子详细内容（包含评论列表）
     *
     * @param postId 帖子 id
     * @return JSONObject
     */
    @GetMapping("/post/{postId}")
    public JSONObject getPostsByPostId(@PathVariable("postId") Integer postId) {
        Post post = postService.getPostById(postId);
        if (post == null) {
            return JsonResult.errorResult(ErrorEnum.E_4002);
        } else {
            return JsonResult.successResult(post);
        }
    }

    @DeleteMapping("/post/{postId}")
    @RequiresPermissions("post:delete")
    public JSONObject deletePostByPostId(@PathVariable("postId") Integer postId){
        Post post = postService.getPostById(postId);
        int n = postService.deletePost(postId);
        if(n == 0){
            return JsonResult.errorResult(ErrorEnum.E_4004);
        }
        // 在缓存中对 rank:user 对应 userId 的帖子数-1
        rankService.addUserScore(post.getUserId(), -1);
        // 在缓存中对 rank:post 删除 postId
        rankService.removePost(post.getPostId());
        return JsonResult.successResult("删除帖子成功");

    }
}
