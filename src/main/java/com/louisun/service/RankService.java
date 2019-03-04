package com.louisun.service;

import java.util.Set;

public interface RankService {

    void removeUser(int id);

    void setUserScore(int id, int score);

    void addUserScore(int id, int score);

    Set getTopUser(int top);

    Set getTopUserWithScore(int top);

    Set getTopUserWithScore(int start,int limit);

    void removePost(int id);

    void setPostScore(int id, int score);

    void addPostScore(int id, int score);

    Set getTopPost(int top);

    Set getTopPostWithScore(int top);

    Set getTopPostWithScore(int start,int limit);
}
