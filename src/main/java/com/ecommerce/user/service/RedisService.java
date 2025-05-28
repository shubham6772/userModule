package com.ecommerce.user.service;

public interface RedisService {
    void saveUser(String key, Object value);
    Object getUser(String key);

}
