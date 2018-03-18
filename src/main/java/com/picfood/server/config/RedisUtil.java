package com.picfood.server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by shawn on 2018/3/16.
 */
@PropertySource("application.yml")
public enum RedisUtil {
    INSTANCE;

    private final JedisPool pool;

    @Value("${redis.hostname}")
    private String redisHostName;

    RedisUtil() {
        pool = new JedisPool(new JedisPoolConfig(), redisHostName);
    }

    public void sadd(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.sadd(key, value);
        } finally {
            if (jedis != null) jedis.close();
        }
    }

    public void srem(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.srem(key, value);
        } finally {
            if (jedis != null) jedis.close();
        }
    }

    public boolean sismember(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.sismember(key, value);
        } finally {
            if (jedis != null) jedis.close();
        }
    }
}