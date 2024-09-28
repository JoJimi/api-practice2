package com.example.crash.repostitory;

import com.example.crash.model.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserEntityCacheRepository {
    @Autowired private RedisTemplate<String, UserEntity> userEntityRedisTemplate;

    public void setUserEntityCache(UserEntity userEntity){
        var redisKey = getRedisKey(userEntity.getUsername());
        userEntityRedisTemplate.opsForValue().set(redisKey, userEntity);
    }

    public Optional<UserEntity> getUserEntityCache(String username){
        var redisKey = getRedisKey(username);
        var userEntity = userEntityRedisTemplate.opsForValue().get(redisKey);
        return Optional.ofNullable(userEntity);
    }
    private String getRedisKey(String username){
        return "user:" + username;
    }
}
