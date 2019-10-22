package study.redis.redismasterslave;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestString {
    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Test
    public void testSet() {
        redisTemplate.opsForValue().set("kehuduan","kehuduan");
    }
    @Test
    public void testGet() {
        redisTemplate.opsForValue().get("kehuduan");
    }

    @Test
    public void testDel() {
        redisTemplate.delete("kuhuduan");

    }

    @Test
    public void testMset() {
        Map<String,String> map = new HashMap<String,String>(2);
        map.put("name","xiaoming");
        map.put("age","12");
        redisTemplate.opsForValue().multiSet(map);
    }

    @Test
    public void testMget() {
        List<String> list = new ArrayList<String>();
        Collections.addAll(list,"name","age");
        List<String> values = redisTemplate.opsForValue().multiGet(list);
        if (values != null) {
            values.forEach(System.out::println);
        }
    }

    @Test
    public void testStrlen() {
        Long size = redisTemplate.execute((RedisCallback<Long>) redisConnection -> redisConnection.strLen("name".getBytes()));
        System.out.println(size);
    }

    @Test
    public void testIncr() {
        Long age = redisTemplate.opsForValue().increment("age");
        System.out.println(age);
    }


}
