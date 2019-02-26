package com.louisun.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig extends CachingConfigurerSupport {
//    @Value("{spring.redis.host}")
//    private String host;
//
//    @Value("{spring.redis.port}")
//    private int port;
//
//    @Value("{spring.redis.password}")
//    private String password;
//
//    @Bean(name="RedisConnectionFactory")
//    public RedisConnectionFactory initRedisConnectionFactory(){
//        if(this.connectionFactory == null) return this.connectionFactory;
//        JedisPoolConfig poolConfig = new JedisPoolConfig();
//        poolConfig.setMaxIdle(maxIdle);
//        poolConfig.setMaxWaitMillis(maxWait);
//        poolConfig.setMaxTotal(maxTotal);
//
//        // 创建 Jedis 连接工厂
//        JedisConnectionFactory connectionFactory = new JedisConnectionFactory(poolConfig);
//        // 获取单机的 Redis 配置
//        RedisStandaloneConfiguration rsCfg = connectionFactory.getStandaloneConfiguration();
//        rsCfg.setHostName(host);
//        rsCfg.setPort(port);
//        rsCfg.setPassword(password);
//        return connectionFactory;
//    }

    @Autowired
    private RedisConnectionFactory factory = null;

    @Bean
    public RedisTemplate<String ,String> redisTemplate(RedisConnectionFactory factory){
        StringRedisTemplate template = new StringRedisTemplate(factory);
        RedisSerializer stringRedisSerializer = template.getStringSerializer();
        template.setKeySerializer(stringRedisSerializer);
        template.setHashKeySerializer(stringRedisSerializer);
        template.setHashValueSerializer(stringRedisSerializer);

        template.setConnectionFactory(factory);
        return template;
    }


    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory){
        RedisCacheManager cacheManager = RedisCacheManager.create(factory);
//        cacheManager.setDefaultExpiration(3600);
        return cacheManager;
    }




}

