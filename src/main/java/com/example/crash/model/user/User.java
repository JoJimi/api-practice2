package com.example.crash.model.user;

import com.example.crash.model.entity.UserEntity;
import org.apache.tomcat.util.buf.UEncoder;

public record User(Long userId, String username, String name, String email) {

    public static User from(UserEntity userEntity){
        return new User(
                userEntity.getUserId(),
                userEntity.getUsername(),
                userEntity.getName(),
                userEntity.getEmail()
        );
    }
}
