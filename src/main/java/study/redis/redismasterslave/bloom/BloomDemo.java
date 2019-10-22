package study.redis.redismasterslave.bloom;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

public class BloomDemo {

    public void bloomFilter() {
        BloomFilter<Integer> filter = BloomFilter.create(Funnels.integerFunnel(),3);
        filter.put(45);
        filter.put(2);
        filter.put(3);
        if (filter.mightContain(45)) {
            System.out.println("存在该数据");
            return;
        }
        System.out.println("数据不存在1");
    }
}
