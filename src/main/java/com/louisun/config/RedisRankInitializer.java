package com.louisun.config;

import com.louisun.dto.PostRankInfo;
import com.louisun.dto.UserRankInfo;
import com.louisun.service.PostService;
import com.louisun.service.RankService;
import com.louisun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RedisRankInitializer implements ApplicationRunner {
    private final RankService rankService;

    private final UserService userService;

    private final PostService postService;

    @Autowired
    public RedisRankInitializer(RankService rankService, UserService userService, PostService postService) {
        this.rankService = rankService;
        this.userService = userService;
        this.postService = postService;
    }

    /**
     * Callback used to run the bean.
     *
     * @param args incoming application arguments
     * @throws Exception on error
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 初始化用户 Rank 数据
        List<UserRankInfo> userList = userService.getUserRankInfoList();
        for(UserRankInfo userRankInfo : userList){
            rankService.setUserScore(Integer.valueOf(userRankInfo.getUserId()), userRankInfo.getPostCount());
        }

        // 初始化帖子 Rank 数据
        List<PostRankInfo> postList = postService.getPostRankInfoList();
        for(PostRankInfo postRankInfo : postList){
            rankService.setPostScore(Integer.valueOf(postRankInfo.getPostId()), postRankInfo.getCommentCount());
        }
    }
}
