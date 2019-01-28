package com.louisun.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Post {
    /**
     * 文章 ID
     */
    private Integer postId;

    /**
     * 版面 ID
     */
    private Integer tagId;

    /**
     * 用户（发帖者）ID
     */
    private Integer userId;

    /**
     * 标题
     */
    private String title;

    /**
     * 评论总数
     */
    private Integer commentAmount;

    /**
     * 发布时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 帖子内容 html 格式
     */
    private String content;

    /**
     * 帖子内容 markdown 格式
     */
    private String contentMd;

    /**
     * 帖子的所有评论
     */
    private List<Comment> commentList;
}