package com.example.crash.service;

import com.example.crash.exception.user.UserNotFoundException;
import com.example.crash.model.entity.UserEntity;
import com.example.crash.repostitory.UserEntityCacheRepository;
import com.example.crash.repostitory.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserEntityRepository userEntityRepository;
    @Autowired private UserEntityCacheRepository userEntityCacheRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userEntityRepository.findByUsername(username)
                .orElseThrow(
                        ()-> new UserNotFoundException(username));
    }

    private UserEntity getUserEntityByUsername(String username){
        return userEntityCacheRepository
                .getUserEntityCache(username)
                .orElseGet(() -> {
                    var userEntity = userEntityRepository
                            .findByUsername(username)
                            .orElseThrow(() -> new UserNotFoundException(username));

                    userEntityCacheRepository.setUserEntityCache(userEntity);

                    return userEntity;
                });

    }
}