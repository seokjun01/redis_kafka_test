package com.example.demo;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

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

    @PostMapping("/set/TTL")
    public String setTTL (@RequestParam String key ,@RequestParam String value,@RequestParam int seconds) {
        redisTemplate.opsForValue().set(key, value , Duration.ofSeconds(seconds));
        return "저장 완료 : " + key + " = " + value + "유효 시간 " + seconds + "초";
    }

    @GetMapping("/get/TTL")
    public String getTTL( @RequestParam String key) {
        Long remainSeconds =redisTemplate.getExpire(key, TimeUnit.SECONDS);
        return "남은 유효시간: " + remainSeconds + "초";
    }
}
