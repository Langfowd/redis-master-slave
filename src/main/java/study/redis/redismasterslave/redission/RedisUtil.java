package study.redis.redismasterslave.redission;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.BitSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    @Autowired
    RedissonClient redissonClient;

    int num = 0;


    public void lockDemo() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                RLock getLock = redissonClient.getLock("getLock");
                try {
                     latch.await();
                     // 100 为尝试获取锁的最大时间，超过了没有获取到锁就失败，10为锁自动过期时间
                    if (getLock.tryLock(100, 10, TimeUnit.SECONDS)) {
                        System.out.println(Thread.currentThread().getName()+":获得锁成功");
                        TimeUnit.SECONDS.sleep(3);
                        num++;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    getLock.unlock();
                }

            },"thread:"+i).start();
        }
        latch.countDown();
        try {
            TimeUnit.SECONDS.sleep(60);
            System.out.println(num);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
