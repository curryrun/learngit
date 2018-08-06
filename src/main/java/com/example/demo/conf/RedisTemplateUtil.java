package com.example.demo.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

public class RedisTemplateUtil {

    public static <K, V> void resetValueByHours(RedisTemplate<K, V> redisTemplate, K key, V value) {
        long ttl = redisTemplate.getExpire(key);
        if (ttl > 0) {
            redisTemplate.opsForValue().set(key, value, ttl, TimeUnit.HOURS);
        }
    }

    public static <K, V> void resetValueByMinutes(RedisTemplate<K, V> redisTemplate, K key, V value) {
        long ttl = redisTemplate.getExpire(key);
        if (ttl > 0) {
            redisTemplate.opsForValue().set(key, value, ttl, TimeUnit.MINUTES);
        }
    }

    public static <K, V> void resetValueBySeconds(RedisTemplate<K, V> redisTemplate, K key, V value) {
        long ttl = redisTemplate.getExpire(key);
        if (ttl > 0) {
            redisTemplate.opsForValue().set(key, value, ttl, TimeUnit.SECONDS);
        }
    }
}
