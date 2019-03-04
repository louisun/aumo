package com.louisun.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class PostRankInfo {
    public String postId; // 帖子id
    public String postTitle; // 帖子标题
    public Integer userId; // 用户id
    public String userNickname; // 用户昵称
    public Integer commentCount; // 评论数
}
