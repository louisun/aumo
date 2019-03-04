package com.louisun.service.Impl;

import com.louisun.dao.RedisDao;
import com.louisun.service.RankService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Slf4j
@Service
public class RankServiceImpl implements RankService {

    private static String RANK_LIST_USER = "rank:user";
    private static String RANK_LIST_POST = "rank:post";

    @Autowired
    private RedisDao redisDao;

    @Override
    public void removeUser(int id) {
        redisDao.rmZset(RANK_LIST_USER, id);
    }


    @Override
    public void setUserScore(int id, int score) {
        redisDao.setZSet(RANK_LIST_USER, id, score);

    }

    @Override
    public void addUserScore(int id, int score) {
        redisDao.addScore(RANK_LIST_USER, id, score);
    }

    @Override
    public Set getTopUser(int top) {
        return redisDao.getTop(RANK_LIST_USER, top);
    }

    @Override
    public Set getTopUserWithScore(int top) {
        return redisDao.getTopWithScore(RANK_LIST_USER, top);
    }

    @Override
    public Set getTopUserWithScore(int start, int limit) {
        return redisDao.getTopWithScore(RANK_LIST_USER, start, limit);
    }


    @Override
    public void removePost(int id) {
        redisDao.rmZset(RANK_LIST_POST, id);
    }

    @Override
    public void setPostScore(int id, int score) {
        redisDao.setZSet(RANK_LIST_POST, id, score);
    }

    @Override
    public void addPostScore(int id, int score) {
        redisDao.addScore(RANK_LIST_POST, id, score);
    }

    @Override
    public Set getTopPost(int top) {
        return redisDao.getTop(RANK_LIST_POST, top);
    }

    @Override
    public Set getTopPostWithScore(int top) {
        return redisDao.getTopWithScore(RANK_LIST_POST, top);
    }

    @Override
    public Set getTopPostWithScore(int start, int limit) {
        return redisDao.getTopWithScore(RANK_LIST_POST, start, limit);
    }
}
