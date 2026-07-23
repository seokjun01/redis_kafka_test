package com.example.demo;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/redis")
@AllArgsConstructor
public class RedisController {

    private final StringRedisTemplate redisTemplate;

    @PostMapping("/set")
    public String set(@RequestParam String key,@RequestParam String value) {
        redisTemplate.opsForValue().set(key,value);
        return "저장 완료 : " + key + " = " + value;
    }


    @GetMapping("/get")
    public String get(@RequestParam String key) {
        String value = redisTemplate.opsForValue().get(key);
        return value != null ? value : "값 없음";
    }
}
