package study.redis.redismasterslave;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import study.redis.redismasterslave.redission.RedisUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestLock {
    @Autowired
    RedisUtil redisUtil;

    @Test
    public void testLock() throws InterruptedException {
        redisUtil.lockDemo();
    }

}
