package study.redis.redismasterslave.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisTemplateController {

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @GetMapping("/setKey")
    public void getKey(String key,String value) {
        System.out.println();
        redisTemplate.opsForValue().set(key,value);
    }
    @GetMapping("/get/{key}")
    public String getKey(@PathVariable String key) {
        return redisTemplate.opsForValue().get(key);
    }
}
