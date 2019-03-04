package com.louisun.dto;

import lombok.Data;

@Data
public class UserRankInfo {
    public String userId; // 用户id
    public String nickname; // 用户昵称
    public int postCount; // 文章数
}
