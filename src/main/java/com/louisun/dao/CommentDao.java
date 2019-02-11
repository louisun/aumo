package com.louisun.dao;

import com.louisun.model.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface CommentDao {
    /**
     * 删除评论
     */
    int deleteByCommentId(Integer commentId);

    /**
     * 创建评论
     */
    int insertComment(Comment comment);

    /**
     * 根据评论 ID 查询评论
     */
    Comment selectByCommentId(Integer commentId);

    /**
     * 根据帖子 ID 查询评论列表（根据发布时间正序）
     */
    List<Comment> selectByPostId(@Param("postId") Integer postId);

    /**
     * 根据用户 ID 查询评论（根据发布时间倒序）
     */
    List<Comment> selectByUserId(Integer userId);
}