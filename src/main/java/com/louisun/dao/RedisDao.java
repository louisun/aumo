package com.louisun.dao;


import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RedisDao {
    Object get(String name);

    Object getList(String name);

    Object getSet(String name);

    void setValue(String key, Object value);

    void setHash(String key, Map<String, ? extends Object> map);

    Object getHashValue(String mapName, String key);

    Map<String,? extends Object> getHash(String key);

    void setList(String key, List<? extends Object> o);

    void rmZset(String key, int id);

    void setZSet(String key,int id,double score);

    void addScore(String key,int id,double score);

    void updateScore(String key,int id,double score);

    public Set getTop(String key, int top);

    Set getTopWithScore(String key, int i);

    Set getTopWithScore(String key,int start, int limit);


}
