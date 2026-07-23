package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
public class RedisConnectionTest {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    void redis_연결테스트() {
        //opsFor자료타입 + key-value방식으로 저장
        redisTemplate.opsForValue().set("test-key", "hello-redis");

        String result = redisTemplate.opsForValue().get("test-key");

        System.out.println("가져온 값: " + result);
    }
}
