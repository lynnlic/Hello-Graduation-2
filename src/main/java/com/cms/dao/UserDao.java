package com.cms.dao;

import com.cms.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    List<UserEntity> getAllUser();
    int addUser(int id, String name, String account, String password, int state);
    List<UserEntity> getUserByCondition(String account, String name);
}
