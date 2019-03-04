package com.louisun.controller;

import com.alibaba.fastjson.JSONObject;
import com.louisun.dto.PostRankInfo;
import com.louisun.dto.UserRankInfo;
import com.louisun.model.Post;
import com.louisun.model.User;
import com.louisun.service.PostService;
import com.louisun.service.RankService;
import com.louisun.service.UserService;
import com.louisun.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Slf4j
@RestController
public class RankController {

    @Autowired
    private RankService rankService;

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @GetMapping("/top/user/{topN}")
    public JSONObject getTopUsers(@PathVariable("topN") int topN){
        Set set = rankService.getTopUserWithScore(topN);
        Iterator<DefaultTypedTuple> iterator = set.iterator();
        List<UserRankInfo> list = new ArrayList<>();
        while(iterator.hasNext()){
            DefaultTypedTuple defaultTypedTuple = iterator.next();

            int userId = (Integer) defaultTypedTuple.getValue();
            UserRankInfo userRankInfo = new UserRankInfo();
            User user= userService.getUserById(userId);

            userRankInfo.setUserId(((Integer) defaultTypedTuple.getValue()).toString());
            userRankInfo.setNickname(user.getNickname());
            userRankInfo.setPostCount(((Integer) defaultTypedTuple.getScore().intValue()));
            list.add(userRankInfo);
        }
        return JsonResult.successResult(list);
    }

    @GetMapping("/top/post/{topN}")
    public JSONObject getTopPosts(@PathVariable("topN") int topN){
        Set set = rankService.getTopPostWithScore(topN);
        Iterator<DefaultTypedTuple> iterator = set.iterator();
        List<PostRankInfo> list = new ArrayList<>();
        while(iterator.hasNext()){
            DefaultTypedTuple defaultTypedTuple = iterator.next();

            int postId = (Integer) defaultTypedTuple.getValue();
            PostRankInfo postRankInfo = new PostRankInfo();
            Post post = postService.getPostById(postId);
            User user = userService.getUserById(post.getUserId());

            postRankInfo.setPostId(((Integer) defaultTypedTuple.getValue()).toString());
            postRankInfo.setPostTitle(post.getTitle());
            postRankInfo.setUserId(post.getUserId());
            postRankInfo.setUserNickname(user.getNickname());
            postRankInfo.setCommentCount(((Integer) defaultTypedTuple.getScore().intValue()));
            list.add(postRankInfo);
        }
        return JsonResult.successResult(list);
    }
}
