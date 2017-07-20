package com.ding.buyaround.service;

import com.ding.buyaround.model.UserEntity;
import com.ding.buyaround.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by pc on 2017/6/1.
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserEntity lookup(String username) {
        return this.userRepository.findUserByUsername(username);
    }

    public void save(UserEntity user) {
        this.userRepository.save(user);
    }

    public boolean usernameExists(String username) {
        UserEntity user = this.userRepository.findUserByUsername(username);
        return Optional.ofNullable(user).map(o->true).orElse(false);
    }

    public Long getUserID(String username) {
        UserEntity user = lookup(username);
        return Optional.ofNullable(user).map(o->o.getId()).orElse(0l);
    }
}
