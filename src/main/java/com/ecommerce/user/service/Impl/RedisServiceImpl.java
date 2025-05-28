package com.ecommerce.user.service.Impl;

import com.ecommerce.user.service.RedisService;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
public class RedisServiceImpl implements RedisService {

    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void saveUser(String key, Object value) {
        redisTemplate.opsForValue().set(key, value, 60000, TimeUnit.MILLISECONDS);
    }

    @Override
    public Object getUser(String key) {
        Object obj = redisTemplate.opsForValue().get(key);
        if(obj != null){
            return obj;
        }else{
            return null;
        }
    }
}
