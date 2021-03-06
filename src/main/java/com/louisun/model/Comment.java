package com.louisun.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class Comment implements Serializable {
    private static final long serialVersionUID = 3418783989880085784L;
    /**
     * 评论 ID
     */
    private Integer commentId;

    /**
     * 用户（发评论者） ID
     */
    private Integer userId;

    /**
     * 帖子 ID
     */
    private Integer postId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 回复评论 ID（可为空）
     */
    private Integer toCommentId;

    /**
     * 评论创建时间
     */
    private Date createTime;
}