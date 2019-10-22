package study.redis.redismasterslave.redission;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.spring.data.connection.RedissonConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class RedissionConfig {
    @Bean
    public RedissonConnectionFactory redissonConnectionFactory(RedissonClient redisson) {
        return new RedissonConnectionFactory(redisson);
    }

    @Bean(destroyMethod = "shutdown")
    public RedissonClient redisson(){
        Config config = null;
        try {
            config = Config.fromJSON(this.getClass().getClassLoader().getResourceAsStream("redission.json"));
            return Redisson.create(config);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
