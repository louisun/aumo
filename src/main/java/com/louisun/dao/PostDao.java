package com.louisun.dao;

import com.louisun.dto.PostRankInfo;
import com.louisun.model.Post;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 说明：
 * 查询帖子时在，只有在详情页面，Post中才会存入评论列表，即 selectPostWithCommentsByPostId
 * 其余查询帖子（或列表），都是无 commentList 的 Post
 */
@Mapper
public interface PostDao {
    List<PostRankInfo> getPostRankInfoList();

    /**
     * 删除帖子
     */
    int deleteByPostId(Integer postId);

    /**
     * 插入帖子
     */
    int insertPost(Post post);

    /**
     * 通过帖子 ID 更新帖子
     */
    int updateByPostId(Post post);

    /**
     * 通过帖子 ID 查找帖子基本信息
     */
    Post selectByPostId(Integer postId);

    /**
     * 通过帖子 ID 查找帖子信息，包含 Comment 列表
     */
    Post selectPostWithCommentsByPostId(Integer postId);

    /**
     * 查询所有帖子（根据发布时间倒序）
     */
    List<Post> selectAllPosts();


    /**
     * 通过版块 ID 查找帖子（根据发布时间倒序）
     */
    List<Post> selectByTagId(Integer tagId);

    /**
     * 通过用户 ID 查找帖子（根据更新时间倒序）
     */
    List<Post> selectByUserId(Integer userId);
}