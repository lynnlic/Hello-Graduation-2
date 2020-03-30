package com.cms.dao;

import com.cms.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    List<UserEntity> getAllUser();
}
