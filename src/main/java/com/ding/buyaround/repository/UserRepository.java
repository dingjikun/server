package com.ding.buyaround.repository;

import com.ding.buyaround.model.UserEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by djk on 2017/7/2.
 */
public interface UserRepository extends CrudRepository<UserEntity, Long>{
    UserEntity findUserByUsername(String username);
}
