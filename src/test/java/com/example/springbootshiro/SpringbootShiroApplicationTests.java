package com.example.springbootshiro;

import com.example.springbootshiro.utils.BeanUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class SpringbootShiroApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    void contextLoads() {
        System.out.println(redisTemplate);
        Object redisTemplate = BeanUtil.getBean("redisTemplate");
        System.out.println(redisTemplate);
    }

}
